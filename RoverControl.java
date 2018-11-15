public class RoverControl {
    private static int roverMove(int n, String[] cmds) {
        Rover rover = new Rover(n);
        for (String cmd : cmds) {
            rover.move(cmd);
        }
        int[] pos = rover.getPos();
        return pos[0] * n + pos[1];
    }
    private static class Rover {
        private int[] matrix;
        private final int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // {up, down, left, right}
        private final String[] commands = new String[]{"UP", "DOWN", "LEFT", "RIGHT"};
        private Map<String, Integer> map;
        int[] pos;

        public Rover(int n) {
            this.matrix = new int[n][n];
            this.map = new HashMap<>();
            for (int i = 0; i < commands.length; i++) {
                map.put(commands[i], i);
            }
            this.pos = new int[]{0, 0};
        }

        public boolean move(String dir) {
            int[] move = moves[map.get(dir)];
            if (!isValid(move)) {
                return false;
            }
            pos[0] += move[0];
            pos[1] += move[1];
            return true;
        }

        public int[] getPos() {
            return pos;
        }

        private boolean isValid(int[] move) {
            int x = pos[0] + move[0] ;
            int y = pos[1] + move[1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix.length) {
                return false;
            }
            return true;
        }
    }
    public static void main(String[] args) {
        String[] cmds = {"RIGHT", "DOWN", "LEFT", "LEFT", "DOWN"}; // 8
        String[] cmds2 = {"RIGHT", "UP", "DOWN", "LEFT", "LEFT", "DOWN", "DOWN"}; // 12
        String[] cmds3 = {"RIGHT", "DOWN", "RIGHT", "DOWN", "RIGHT", "RIGHT", "DOWN"}; // 15
        System.out.println(roverMove(4, cmds));
        System.out.println(roverMove(4, cmds2));
        System.out.println(roverMove(4, cmds3));
    }
}
