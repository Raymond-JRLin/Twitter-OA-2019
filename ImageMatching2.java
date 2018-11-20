import java.util.*;

public class ImageMatching2 {
    private static int countMatches(List<String> matrix1, List<String> matrix2) {
        int result = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < matrix1.size(); i++) {
            for (int j = 0; j < matrix1.get(i).length(); j++) {
                if (set.contains(String.valueOf(i) + String.valueOf(j))) {
                    continue;
                }
//                System.out.println("now check " + i + "th string at " + j);
                if (matrix1.get(i).charAt(j) == '1' && matrix2.get(i).charAt(j) == '1') {
                    List<int[]> regions1 = getRegionList(matrix1, i, j);
                    List<int[]> regions2 = getRegionList(matrix2, i, j);
                    if (isMatch(regions1, regions2, set)) {
                        result++;
//                        System.out.println("get 1 result");
                    }
                }
            }
        }
        return result;
    }

    private static List<int[]> getRegionList(List<String> matrix, int row, int col) {
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        List<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        Set<String> visited = new HashSet<>();
        visited.add(String.valueOf(row) + String.valueOf(col));
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
//            System.out.println("queue pos: " + curr[0] + ", " + curr[1]);
            list.add(new int[]{curr[0], curr[1]});
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + dx[i];
                int y = curr[1] + dy[i];
//                System.out.println("go " + x + ", " + y);
                if (x < 0 || x >= matrix.size() || y < 0 || y >= matrix.get(x).length()) {
                    continue;
                }
                if (visited.contains(String.valueOf(x) + String.valueOf(y))) {
//                    System.out.println("visited");
                    continue;
                }
                if (matrix.get(x).charAt(y) != '1') {
                    continue;
                }
                queue.offer(new int[]{x, y});
                visited.add(String.valueOf(x) + String.valueOf(y));
            }
        }
        return list;
    }

    private static boolean isMatch(List<int[]> regions1, List<int[]> regions2, Set<String> set) {
        boolean match = true;
        if (regions1.size() != regions2.size()) {
            match = false;
        }
        for (int i = 0; i < regions1.size(); i++) {
            if (regions1.get(i)[0] != regions2.get(i)[0] || regions1.get(i)[1] != regions2.get(i)[1]) {
                match = false;
            }
            set.add(String.valueOf(regions1.get(i)[0]) + String.valueOf(regions1.get(i)[1]));
        }
        return match;
    }

    public static void main(String[] args) {
        // testcase1
        String[] map1a = new String[]{"001", "0111", "101"};
        String[] map1b = new String[]{"001", "0111", "001"};
        System.out.println(countMatches(Arrays.asList(map1a), Arrays.asList(map1b)));
        // 1

        // testcase2
        String[] map2a = new String[]{"001", "0111", "101"};
        String[] map2b = new String[]{"001", "0111", "011"};
        System.out.println(countMatches(Arrays.asList(map2a), Arrays.asList(map2b)));
        // 0

        // testcase3
        String[] map3a = new String[]{"0100", "1001", "0011", "0011"};
        String[] map3b = new String[]{"0101", "1001", "0011", "0011"};
        System.out.println(countMatches(Arrays.asList(map3a), Arrays.asList(map3b)));
        // 2

        // testcase4
        String[] map4a = new String[]{"0010", "0111", "0100", "1111"};
        String[] map4b = new String[]{"0010", "0111", "0110", "1111"};
        System.out.println(countMatches(Arrays.asList(map4a), Arrays.asList(map4b)));
        // 0
    }
}
