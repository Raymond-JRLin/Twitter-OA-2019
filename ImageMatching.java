public class ImageMatching {
    private static int countMatches(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.lnegth;
        int result = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && matrix1[i][j] == 1) {
                    List<Integer> regions1 = getRegionList(matrix1, n, i, j);
                    List<Integer> regions2 = getRegionList(matrix2, n, i, j);
                    if (isMatch(regions1, regions2, visited)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    private static List<Integer> getRegionList(int[][] matrix, int n, int row, int col) {
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        List<Integer> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        boolean[][] visited = new boolean[n][n];
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            list.add(curr[0] * n + curr[1]);
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + dx[i];
                int y = curr[1] + dy[i];
                if (x < 0 || x >= n || y < 0 || y >= n) {
                    continue;
                }
                if (visited[x][y]) {
                    continue;
                }
                if (matrix[x][y] != 1) {
                    continue;
                }
                queue.offer(new int[]{x, y]});
                visited[x][y] = true;
            }
        }
        return list;
    }
    private static boolean isMatch(List<Integer> regions1, List<Integer> regions2, boolean[][] visited) {
        boolean match = true;
        if (regions1.size() != regions2.size()) {
            match = false;
        }
        for (int i = 0; i < regions1.size(); i++) {
            if (regions1.get(i) != regions2.get(i)) {
                match = false;
            }
            visited[regions1.get(i)] = true;
        }
        return match;
    }

    public static void main(String[] args) {
        // testcase1
        int[][] map1a = new int[][]{
            {0,0,1},
            {0,1,1,1},
            {1,0,1}
        };

        int[][] map1b = new int[][]{
        {0,0,1},
        {0,1,1,1},
        {0,0,1}
        };
        System.out.println(countMatches(map1a, map1b));
        // 1

        // testcase2
        int[][] map2a = new int[][]{
        {0,0,1},
        {0,1,1,1},
        {1,0,1}
        };

        int[][] map2b = new int[][]{
        {0,0,1},
        {0,1,1,1},
        {0,1,1}
        };
        System.out.println(countMatches(map2a, map2b));
        // 0

        // testcase3
        int[][] map3a = new int[][]{
        {0,1,0,0},
        {1,0,0,1},
        {0,0,1,1},
        {0,0,1,1}
        };

        int[][] map3b = new int[][]{
        {0,1,0,1},
        {1,0,0,1},
        {0,0,1,1},
        {0,0,1,1}
        };
        System.out.println(countMatches(map3a, map3b));
        // 2

        // testcase4
        int[][] map4a = new int[][]{
        {0,0,1,0},
        {0,1,1,1},
        {0,1,0,0},
        {1,1,1,1}
        };

        int[][] map4b = new int[][]{
        {0,0,1,0},
        {0,1,1,1},
        {0,1,1,0},
        {1,1,1,1}
        };
        System.out.println(countMatches(map4a, map4b));
        // 0
    }
}
