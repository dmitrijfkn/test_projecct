package secondtask;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondTask {
    private static final Logger logger = Logger.getLogger(SecondTask.class.getName());

    public static void main(String[] args) {
        File file = new File("input.txt");                  //file with input text
        int n = 3;                                                    //number of words that should be counted
        List<WordWithCounter> topWords = new ArrayList<>();

        try {
            topWords = returnTopWords(file, n);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can`t read file", e);
        }

        showList(topWords);
    }

    private static List<String> stringToWords(String text) {
        Stack<String> words = new Stack();

        Pattern pattern = Pattern.compile("\\b[A-Za-z`']+");

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            words.add(text.substring(matcher.start(), matcher.end()).toLowerCase());
        }
        return words;
    }

    private static List<WordWithCounter> countWords(List<String> words) {
        ArrayList<WordWithCounter> count = new ArrayList();
        for (int i = 0; i < words.size(); i++) {
            count.add(new WordWithCounter(words.get(i)));
            for (int j = i; j < words.size(); j++) {
                if (count.get(i).word.equals(words.get(j))) {
                    count.get(i).count++;
                    words.remove(j);
                }
            }
        }
        Collections.sort(count);
        return count;
    }

    private static void showList(List<WordWithCounter> words) {
        for (WordWithCounter word : words) {
            System.out.println(word);
        }
    }

    private static String readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[10];
        StringBuilder sb = new StringBuilder();
        while (fis.read(buffer) != -1) {
            sb.append(new String(buffer));
            buffer = new byte[10];
        }
        fis.close();
        return sb.toString();
    }

    private static List<WordWithCounter> cutTop(List<WordWithCounter> words, int n) {
        if (n < words.size()) {
            ArrayList<WordWithCounter> cutedList = new ArrayList();
            for (int i = 0; i < n; i++) {
                cutedList.add(words.get(i));
            }
            words = cutedList;
        }
        return words;
    }

    private static List<WordWithCounter> returnTopWords(File file, int n) throws IOException {
        String text = readFile(file);
        List<WordWithCounter> words = countWords(stringToWords(text));
        words = cutTop(words, n);
        return words;
    }
}
