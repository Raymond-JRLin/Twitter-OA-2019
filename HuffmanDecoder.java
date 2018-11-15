import java.util.*;

public class HuffmanDecoder {
    private static String huffmanDecoder(String[] dict, String input) {
        // construct mapping relation
        Map<String, String> map = new HashMap<>(); // <code, original string>
        for (String d : dict) {
            String[] s = d.split("\t"); // attention here, need double check
            if (s[0].equals("newline")) {
                map.put(s[1], "\n");
            } else {
                map.put(s[1], s[0]);
            }
        }
        // decode
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for (int right = 1; right <= input.length(); right++) {
            String curr = input.substring(left, right);
            // check if string[left, right] is a Huffman code we have
            if (map.containsKey(curr)) {
                sb.append(map.get(curr));
                left = right;
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] dict = {"a 100100", "b 100101", "c 110001", "d 100000", "newline 1111111", "p 111110", "q 000001"};
        String input = "1111100000011001001111111100101110001100000";
        System.out.println(huffmanDecoder(dict, input));
    }
}
