public class NoPairsAllowed {
    private static List<Integer> minimalOperations(String[] words) {
        List<Integer> result = new ArrayList<>();
        for (String word : words) {
            int count = 0;
            for (int i = 1; i < word.length(); i++) {
                if (word.charAt(i) == word.charAt(i - 1)) {
                    count++;
                    i++; // jump to next next one 
                }
            }
            result.add(count);
        }
        return result;
    }

    public static void main(String[] args) {
        // testcase1
        String[] input1 = {"ab", "aab", "abb", "abab", "abaaaba"};
        printList(minimalOperations(input1));
        // 0 1 1 0 1

        // testcase2
        String[] input2 = {"add", "boook", "break"};
        printList(minimalOperations(input2));
        // 1 1 0
    }
}
