import java.util.*;

public class HackLandElection {

    private static String electionWinner(String[] names) {
        Map<String, Integer> map = new HashMap<>(); // <name, frequency>
        for (String name : names) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        PriorityQueue<Candidate> pq = new PriorityQueue<>();
        for (String name : map.keySet()) {
            pq.offer(new Candidate(name, map.get(name)));
        }

        return pq.poll().name;
    }
    private static class Candidate implements Comparable<Candidate> {
        private String name;
        private int freq;

        public Candidate(String n, int f) {
            this.name = n;
            this.freq = f;
        }

        @Override
        public int compareTo(Candidate o2) {
            if (o2.freq == this.freq) {
                return o2.name.compareTo(this.name);
            } else {
                return Integer.compare(o2.freq, this.freq);
            }
        }
    }
    public static void main(String[] args) {
        String[] votes = { "victor", "veronica", "ryan", "dave", "maria", "farah", "farah", "ryan", "veronica" }; // veronica
        String[] votes2 = {"Alex", "Michael", "Harry", "Dave", "Michael", "Victor", "Harry", "Alex", "Mary", "Mary"}; // Michael
        System.out.println(hackerlandElection(votes));
        System.out.println(hackerlandElection(votes2));
    }
}
