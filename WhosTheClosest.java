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
            }
            map.put(c, list);
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            char c = s.charAt(queries[i]);
            if (!map.containsKey(c)) {
                result[i] = -1;
            } else {
                result[i] = bs(map.get(c), queries[i]);
            }
        }
        return result;
    }

}
