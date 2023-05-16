package exercise;

import java.lang.reflect.Array;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String str;
    private String reversedStr;

    public ReversedSequence(String str) {
        this.str = str;
        char[] array = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (var i = str.length() - 1; i >= 0; i--) {
            sb.append(array[i]);
        }
        this.reversedStr = sb.toString();
    }

    @Override
    public int length() {
        return reversedStr.length();
    }

    @Override
    public char charAt(int i) {
        char[] array = reversedStr.toCharArray();
        if (i < length()) {
            return array[i];
        }
        return '\0';
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        char[] array = reversedStr.toCharArray();
        StringBuilder sb1 = new StringBuilder();
        i = i < 0 ? 0 : i;
        for (int x = i; x < i1 && x < length(); x++) {
            sb1.append(array[x]);
        }
        sb1.reverse();
        return new ReversedSequence(sb1.toString());
    }

    @Override
    public String toString() {
        return reversedStr;
    }
}
// END
