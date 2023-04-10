package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void main(String[] args) {
        Map<String, Integer> words = getWordCount("");
        System.out.println(words);
        System.out.println(toString(words));
    }

    public static Map<String, Integer> getWordCount(String sentence) {
        String[] sentenceAsArray = sentence.split(" ");
        Map<String, Integer> wordsMap = new HashMap<>();
        if (sentence.equals("")) {
            return wordsMap;
        } else {
            for (var i = 0; i < sentenceAsArray.length; i++) {
                int x = wordsMap.containsKey(sentenceAsArray[i]) ? wordsMap.get(sentenceAsArray[i]) : 0;
                wordsMap.put(sentenceAsArray[i], 1 + x);
            }
        }
        return wordsMap;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.size() == 0) {
            return "{}";
        } else {
            String result = "{";
            for (String word : map.keySet()) {
                int count = map.get(word);
                result = result + "\n  " + word + ": " + count;
            }
            return result + "\n}";
        }
    }
}
//END
