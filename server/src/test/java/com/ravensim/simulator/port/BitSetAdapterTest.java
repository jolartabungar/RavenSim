package com.ravensim.simulator.port;

import com.ravensim.simulator.util.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BitSetAdapterTest {

  private BitSetAdapter bitSet;

  @BeforeEach
  void setUp() throws InvalidBitWidthException {
    bitSet = new BitSetAdapter(Common.BIT_WIDTH);
  }

  @AfterEach
  void tearDown() {
    bitSet = null;
  }

  @Test
  void constructor_bitWidthIsLessThanZero() {
    Assertions.assertThrows(InvalidBitWidthException.class, () -> new BitSetAdapter(-1));
  }

  @Test
  void constructor_bitWidthIsZero() {
    Assertions.assertThrows(InvalidBitWidthException.class, () -> new BitSetAdapter(0));
  }

  @Test
  void constructor_bitWithIsOne() {
    Assertions.assertDoesNotThrow(() -> new BitSetAdapter(1));
  }

  @Test
  void constructor_bitWidthIsGreaterThanMax() {
    Assertions.assertThrows(
        InvalidBitWidthException.class, () -> new BitSetAdapter(BitSetAdapter.MAX_BIT_WIDTH + 1));
  }

  @Test
  void constructor_bitWidthIsMax() {
    Assertions.assertDoesNotThrow(() -> new BitSetAdapter(BitSetAdapter.MAX_BIT_WIDTH));
  }

  @Test
  void constructor_allBitsAreZero() {
    Assertions.assertEquals(bitSet.getSignal(), 0);
  }

  @Test
  void setBit_indexIsLessThanZero() {
    Assertions.assertThrows(BitIndexOutOfBoundsException.class, () -> bitSet.setBit(-1));
  }

  @Test
  void setBit_indexIsBitWidth() {
    Assertions.assertThrows(
        BitIndexOutOfBoundsException.class, () -> bitSet.setBit(Common.BIT_WIDTH));
  }

  @Test
  void setBit_BoundaryCase() throws BitIndexOutOfBoundsException {
    bitSet.setBit(0);
    bitSet.setBit(Common.BIT_WIDTH - 1);
    Assertions.assertEquals(bitSet.getBit(0), true);
    Assertions.assertEquals(bitSet.getBit(Common.BIT_WIDTH - 1), true);
  }

  @Test
  void clear_allBitsAreZero() throws BitIndexOutOfBoundsException {
    for (int i = 0; i < Common.BIT_WIDTH; i++) {
      bitSet.setBit(i);
    }
    bitSet.clear();
    Assertions.assertEquals(bitSet.getSignal(), 0);
  }

  @Test
  void getBitWidth_equalsBitWidth() {
    Assertions.assertEquals(bitSet.getBitWidth(), Common.BIT_WIDTH);
  }

  @Test
  void setSignal_overflow() {
    // An integral type with n bit can encode 2^n numbers. An unsigned type represents the
    // non-negative values from 0 through (2^n)-1.
    // Ensure an exception is thrown if the signal will overflow.
    Assertions.assertThrows(
        InvalidSignalException.class, () -> bitSet.setSignal(Common.MAX_VALUE + 1));
  }

  @Test
  void setSignal_underflow() {
    Assertions.assertThrows(InvalidSignalException.class, () -> bitSet.setSignal(-1));
  }

  @Test
  void setSignal_underflowBoundaryCase() {
    Assertions.assertDoesNotThrow(() -> bitSet.setSignal(0));
  }

  @Test
  void setSignal_overflowBoundaryCase() {
    Assertions.assertDoesNotThrow(() -> bitSet.setSignal(Common.MAX_VALUE));
  }

  @Test
  void getSignal_sanityCheck() throws InvalidSignalException {
    var signal = Common.randomSignal();
    bitSet.setSignal(signal);
    Assertions.assertEquals(bitSet.getSignal(), signal);
  }

  @Test
  void getSignal_noOverlappingSignals() throws InvalidSignalException {
    // Ensure that setting the signal multiple times does not cause an overlap in the encoding.
    bitSet.setSignal(Common.randomSignal());
    var signal = Common.randomSignal();
    bitSet.setSignal(signal);
    Assertions.assertEquals(bitSet.getSignal(), signal);
  }
}
