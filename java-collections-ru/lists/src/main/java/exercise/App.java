package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static void main(String[] args) {
        System.out.println(scrabble("SLAV1234A", "slava"));
    }

    public static boolean scrabble(String letters, String word) {
        List<String> lettersList = convertToList(letters);
        List<String> wordLettersList = convertToList(word);
        int x = 0;
        for (String wordLetter : wordLettersList) {
            for (var i = 0; i < lettersList.size(); i++) {
                if (wordLetter.equalsIgnoreCase(lettersList.get(i))) {
                    lettersList.remove(i);
                    x++;
                    i = lettersList.size();
                }
            }
        }
        return x == wordLettersList.size();
    }

    public static List<String> convertToList(String str) {
        List<String> list = new ArrayList<>();
        for (var i = 0; i < str.length(); i++) {
            list.add(String.valueOf(str.charAt(i)));
        }
        return list;
    }
}
//END
