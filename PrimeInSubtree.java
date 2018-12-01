import java.util.*;

public class PrimeInSubtree {
    private static List<Integer> primeQuery(int n, List<Integer> first, List<Integer> second, List<Integer> values, List<Integer> queries) {
        // construct adjacent list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            // nodes start from 1, totally n nodes
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < first.size(); i++) {
            adj.get(first.get(i)).add(second.get(i));
            adj.get(second.get(i)).add(first.get(i));
        }
        // build the tree
        Map<Integer, List<Integer>> map = buildTree(n, adj);
        // query
        List<Integer> result = new ArrayList<>();
        for (int root : queries) {
            // System.out.println("now check root as " + root);
            result.add(searchPrime(map, root, values));
        }
        return result;
    }
    private static Map<Integer, List<Integer>> buildTree(int n, List<List<Integer>> adj) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            List<Integer> list = map.getOrDefault(curr, new ArrayList<>());
            for (int next : adj.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                list.add(next);
                visited[next] = true;
                queue.offer(next);
            }
            map.put(curr, list);
        }
        return map;
    }
    private static int searchPrime(Map<Integer, List<Integer>> map, int root, List<Integer> values) {
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // System.out.println("curr node is " + curr);
            if (isPrime(values.get(curr - 1))) {
                result++;
                // System.out.println("corresponding value is " + values[curr - 1] + ", it's a prime");
            }
            if (!map.containsKey(curr) || map.get(curr).size() == 0) {
                // System.out.println("leaf");
                continue;
            }
            for (int next : map.get(curr)) {
                queue.offer(next);
            }
        }
        return result;
    }
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i = i + 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
    private static void printArray(List<Integer> nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 10;
        List<Integer> first = Arrays.asList(6, 8, 3, 6, 4, 1, 8, 5, 1);
        List<Integer> second = Arrays.asList(9, 9, 5, 7, 8, 8, 10, 8, 2);
        List<Integer> values = Arrays.asList(17, 29, 3, 20, 11, 8, 3, 23, 5, 15);
        List<Integer> queries = Arrays.asList(1, 8, 9, 6, 4, 3);
        printArray(primeQuery(n, first, second, values, queries));
        // 7, 5, 2, 1, 0, 1
    }
}
