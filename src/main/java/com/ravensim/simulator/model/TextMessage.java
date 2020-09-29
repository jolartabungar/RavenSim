package com.ravensim.simulator.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.lang.reflect.Type;

public abstract class TextMessage<T> implements Serializable {

  private String type;

  private T message;

  public TextMessage(String type, T message) {
    this.type = type;
    this.message = message;
  }

  public T getMessage() {
    return message;
  }

  public static class TextMessageDeserializer implements JsonDeserializer<TextMessage> {
    private static final String EVENT = "Event";
    private static final String COMMAND = "Command";
    private static final String TYPE = "type";

    @Override
    public TextMessage deserialize(
        JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
      var jsonObject = jsonElement.getAsJsonObject();
      var messageType = jsonObject.get(TYPE);
      if (messageType != null) {
        switch (messageType.getAsString()) {
          case EVENT:
            return jsonDeserializationContext.deserialize(jsonObject, Event.class);
          case COMMAND:
            return jsonDeserializationContext.deserialize(jsonObject, Command.class);
        }
      }
      // todo Handle the case where the message type is invalid.
      throw new UnsupportedOperationException(
          String.format("%s is an invalid message type", messageType));
    }
  }
}
