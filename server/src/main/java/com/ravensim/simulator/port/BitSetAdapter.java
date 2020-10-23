package com.ravensim.simulator.port;

import java.util.BitSet;
import java.util.stream.IntStream;

public class BitSetAdapter implements BinaryWord {
  public static final int MAX_BIT_WIDTH = 1 << 5;
  protected static final int DEFAULT_SIGNAL = 0;
  private final BitSet adaptee;
  private final int bitWidth;

  public BitSetAdapter(int bitWidth) throws InvalidBitWidthException {
    if (bitWidth <= 0) {
      throw new InvalidBitWidthException("the bit width must be greater than 0");
    } else if (bitWidth > MAX_BIT_WIDTH) {
      throw new InvalidBitWidthException(
          String.format(
              "the bit width must be less than or equal to the max bit width: %s", MAX_BIT_WIDTH));
    }
    this.bitWidth = bitWidth;
    adaptee = new BitSet(bitWidth);
  }

  @Override
  public String toString() {
    return String.format(
        "[Signal: %s, Hashcode: %s]",
        toBinaryStringWithPadding(getSignal(), bitWidth, true), hashCode());
  }

  private static String toBinaryStringWithPadding(int signal, int bitWidth, boolean hasPrefix) {
    var strEncoding = Integer.toBinaryString(signal);
    var prefix = hasPrefix ? "0b" : "";
    return String.format("%s%" + bitWidth + "s", prefix, strEncoding).replace(' ', '0');
  }

  private static void checkBitIndex(int index, int bitWidth) throws BitIndexOutOfBoundsException {
    if (index < 0) {
      throw new BitIndexOutOfBoundsException("the bit index must be greater than or equal to 0");
    } else if (index > bitWidth - 1) {
      throw new BitIndexOutOfBoundsException("the bit index must be less than the bit width");
    }
  }

  @Override
  public void clear() {
    adaptee.clear();
  }

  @Override
  public boolean getBit(int index) throws BitIndexOutOfBoundsException {
    checkBitIndex(index, bitWidth);
    return adaptee.get(index);
  }

  @Override
  public int getBitWidth() {
    return bitWidth;
  }

  @Override
  public void setBit(int index) throws BitIndexOutOfBoundsException {
    checkBitIndex(index, bitWidth);
    adaptee.set(index);
  }

  @Override
  public int getSignal() {
    return IntStream.range(0, bitWidth)
        .reduce(0, (accumulator, i) -> accumulator |= adaptee.get(i) ? 1 << i : 0);
  }

  @Override
  public void setSignal(long signal) throws InvalidSignalException {
    if (signal > (1L << bitWidth) - 1) {
      throw new InvalidSignalException("the signal will overflow the bit width");
    } else if (signal < 0) {
      throw new InvalidSignalException("the signal will underflow the bit width");
    }
    // Clear the current signal before setting the new signal
    clear();
    var strEncoding = toBinaryStringWithPadding((int) signal, bitWidth, false);
    IntStream.range(0, bitWidth)
        .forEach(
            i -> {
              if (strEncoding.charAt(i) == '1') {
                try {
                  setBit(bitWidth - i - 1);
                } catch (BitIndexOutOfBoundsException e) {
                  // Do nothing.
                }
              }
            });
  }
}
