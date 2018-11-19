public class WhoIsTheClosest {
    private static int[] closest(String s, int[] queries) {
        int n = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            if (!map.containsKey(c)) {
                indexes[i] = 0;
            } else {
                int curr = list.size() - 1;
                if (list.size() == 2) {
                    indexes[list.get(curr - 1)] = curr;
                } else if (list.size() > 2) {
                    int frontDist = list.get(curr - 1) - list.get(curr - 2);
                    int backDist = list.get(curr) - list.get(curr - 1);
                    if (backDist < frontDist) {
                        indexes[list.get(curr - 1)] = curr;
                    }
                }
                indexes[i] = curr - 1;
            }
            map.put(c, list);
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int search = queries[i];
            char c = s.charAt(search);
            int index = map.get(c).get(indexes[search]);
            result[i] = search == index ? -1 : index;
        }
        return result;
    }
    private static void printArray(int[] array) {
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        // testcase 1
        String test1 = "babab";
        int[] index1 = {2};
        printArray(closest(test1, index1));
        // 0

        // testcase 2
        String test2 = "hackerrank";
        int[] index2 = {4, 1, 6, 8};
        printArray(closest(test2, index2));
        // -1 7 5 -1

    }
}
