public class ImageMatching {
    private static int countMatches(int[][] matrix1, int[][] matrix2) {
        int result = 0;
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] == 1 && matrix2[i][j] == 1) {
                    List<int[]> regions1 = getRegionList(matrix1, i, j);
                    List<int[]> regions2 = getRegionList(matrix2, i, j);
                    if (isMatch(regions1, regions2)) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    private static List<int[]> getRegionList(int[][] matrix, int row, int col) {
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        List<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        matrix[row][col] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            list.add(new int[]{curr[0], curr[1]});
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + dx[i];
                int y = curr[1] + dy[i];
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[x].length) {
                    continue;
                }
                if (matrix[x][y] != 1) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                matrix[x][y] = 0;
            }
        }
        return list;
    }
    private static boolean isMatch(List<int[]> regions1, List<int[]> regions2) {
        if (regions1.size() != regions2.size()) {
            return false;
        }
        for (int i = 0; i < regions1.size(); i++) {
            if (regions1.get(i)[0] != regions2.get(i)[0] || regions1.get(i)[1] != regions2.get(i)[1]) {
                return false;
            }
        }
        return true;
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
