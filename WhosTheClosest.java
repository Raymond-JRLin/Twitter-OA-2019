public class WhoIsTheClosest {
    private static int[] closest(String s, int[] queries) {
        int n = s.length();
        Map<Character, List<Integer>> map = new HashMap<>(); // <char, list of char's index in s>
        int[] indexes = new int[n]; // s 的每位 char 它最近的 char 在 map list 中的位置
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i); // 把当前 char 的位置放入 map list 中
            if (!map.containsKey(c)) {
                // 如果 map 之前没存过， 也就是当前 char 是第一个， 那么离它最近的就默认是它自己， 即在 list 中 index 为 0 的
                indexes[i] = 0;
            } else {
                // 如果之前 map 中已经有了， 也就意味着之前出现过这个 char， 那么就有了找到最近的 char 的可能
                int curr = list.size() - 1; // 当前 char 在 list 中的 index
                if (list.size() == 2) {
                    // 如果现在 list 只有 2 个元素， 那么只需要更新前一位 char 最近的就是当前新加入的 char
                    indexes[list.get(curr - 1)] = curr;
                } else if (list.size() > 2) {
                    // 如果 list 中有超过 2 个， 那么更新的依然只有前一位， 因为更前面的最近的 char 绝对不可能是当前新加入的， 所以比较前一位 char 是离它前一位更近一些， 还是离它后一位 （即当前 char） 更近一些
                    int frontDist = list.get(curr - 1) - list.get(curr - 2); // 与前一位 char 的距离
                    int backDist = list.get(curr) - list.get(curr - 1); // 与后一位 char 的距离
                    if (backDist < frontDist) {
                        // 如果距离当前 char 更近， 更新一下
                        indexes[list.get(curr - 1)] = curr;
                    }
                }
                indexes[i] = curr - 1; // 更新当前新加入的 char 它自己最近的， 就是前一位
            }
            map.put(c, list); // update map
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int search = queries[i]; // 要查找的在 s 中的 index
            char c = s.charAt(search); // 对应的 char
            int index = map.get(c).get(indexes[search]); // 拿到要查找的 index 最近的在 list 中的位置 -> 对应的在 s 中的 index
            result[i] = search == index ? -1 : index; // 如果相等就是它自己， 意味着只有一个 char
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
