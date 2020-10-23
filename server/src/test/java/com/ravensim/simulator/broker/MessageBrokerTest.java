package com.ravensim.simulator.broker;

import com.ravensim.simulator.event.Shutdownable;
import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class MessageBrokerTest {

  private MessageBroker messageBroker;
  private WebSocketSession session;

  @BeforeEach
  void setUp() {
    session = Mockito.mock(WebSocketSession.class);
    messageBroker = new MessageBroker(session);
  }

  @AfterEach
  void tearDown() {
    session = null;
    messageBroker = null;
  }

  @RepeatedTest(Common.NUMBER_OF_TEST)
  void add_allEventsAreAddedToQueue() throws InterruptedException {
    addMessages();
    var messages = (BlockingQueue) ReflectionTestUtils.getField(messageBroker, "messages");
    Assertions.assertEquals(
        messages.size(), Common.ITERATIONS_FOR_STRESS_TEST * Common.LEVEL_OF_PARALLELISM);
  }

  private void addMessages() throws InterruptedException {
    Runnable add =
        () -> {
          IntStream.range(0, Common.ITERATIONS_FOR_STRESS_TEST)
              .forEach(i -> messageBroker.add(new String()));
        };
    ExecutorService executor = Executors.newWorkStealingPool(Common.LEVEL_OF_PARALLELISM);
    for (int i = 0; i < Common.LEVEL_OF_PARALLELISM; i++) {
      executor.submit(add);
    }
    executor.shutdown();
    executor.awaitTermination(Common.TIMEOUT, Common.TIME_UNIT);
  }

  @Test
  void run_sendMessageIsInvoked() throws InterruptedException, IOException {
    messageBroker.add(new String());
    Mockito.when(session.isOpen()).thenReturn(true);
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(messageBroker);
    executor.shutdown();
    executor.awaitTermination(50, TimeUnit.MILLISECONDS);
    Mockito.verify(session).sendMessage(Mockito.any());
  }

  @Test
  void run_notifyObserversIfSessionCloses() throws InterruptedException {
    addMessages();
    Mockito.when(session.isOpen()).thenReturn(false);
    var observer = Mockito.mock(Shutdownable.class);
    messageBroker.addObserver(observer);
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(messageBroker);
    executor.shutdown();
    executor.awaitTermination(Common.TIMEOUT, Common.TIME_UNIT);
    Mockito.verify(observer).update(Mockito.any());
  }

  @Test
  void run_notifyObserversIfShutdownNowIsInvoked() throws InterruptedException {
    addMessages();
    var observer = Mockito.mock(Shutdownable.class);
    messageBroker.addObserver(observer);
    messageBroker.shutdownNow();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(messageBroker);
    executor.shutdown();
    executor.awaitTermination(Common.TIMEOUT, Common.TIME_UNIT);
    Mockito.verify(observer).update(Mockito.any());
  }
}
