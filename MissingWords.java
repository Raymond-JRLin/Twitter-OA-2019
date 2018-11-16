import java.util.*;

public class MissingWords {
    private static List<String> missingWords(String s, String t) {
        String[] src = s.split(" ");
        String[] tar = t.split(" ");
        int i = 0;
        int j = 0;
        List<String> result = new ArrayList<>();
        while (j < tar.length) {
            // problem constraints t is a subsequence of s
            if (src[i].equals(tar[j])) {
                i++;
                j++;
            } else {
                result.add(src[i]);
                i++;
            }
        }
        // don't forget rest of s
        while (i < src.length) {
            result.add(src[i]);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        String str1 = "I am using HackerRank to improve programming";
        String str2 = "am HackerRank to improve";
        for (String str : missingWords(str1, str2)) {
            System.out.println(str);
        }
    }
}
