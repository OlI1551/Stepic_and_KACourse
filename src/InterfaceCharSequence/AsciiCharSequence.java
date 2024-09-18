package InterfaceCharSequence;

import java.util.stream.IntStream;

public class AsciiCharSequence implements java.lang.CharSequence {
    byte[] byteArray;

    public AsciiCharSequence(byte[] myByteArray) {
        this.byteArray = myByteArray;
    }

    @Override
    public int length() {
        return byteArray.length;
    }
    @Override
    public char charAt(int index) {
        return (char) byteArray[index];
    }
    @Override
    public java.lang.CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(java.util.Arrays.copyOfRange(byteArray, start, end));
    }
    @Override
    public String toString() {
        return new String(byteArray);
    }
}

