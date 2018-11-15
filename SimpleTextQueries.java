public class SimpleTextQueries {
    private static List<String> textQueries(String[] sentences, String[] phrases) {
        // problem says "If all of the words of a phrase occur multiple times in a sentence, the index of that sentences[i] should occur that number of times", so we should record the frequency of the words in a sentence
        int n = sentences.length;
        // construct frequency for each word in every sentence
        List<Map<String, Integer>> freq = new ArrayList<>(); // <<word, frequency>>
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            String[] sentence = sentences[i].split(" ");
            for (String s : sentence) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
            freq.add(map);
        }

        List<String> result = new ArrayList<>();
        for (String phrase : phrases) {
            String[] words = phrase.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                // loop each sentence to check if this sentence has each word in this phrase
                Map<String, Integer> map = freq.get(i);
                int count = Integer.MAX_VALUE;
                boolean isValid = true;
                for (String word : words) {
                    if (!map.containsKey(word)) {
                        isValid = false;
                        break;
                    } else {
                        count = Math.min(count, map.get(word));
                    }
                }
                if (!isValid) {
                    continue;
                }
                while (count-- > 0) {
                    sb.append(i).append(" ");
                }
            }
            if (sb.length() == 0) {
                result.add("-1");
            } else {
                result.add(sb.toString().trim());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // testcase 1
        String[] sentences1 = {"bob and alice like to text each other", "bob does not like to ski", "alice likes to ski"};
        String[] phrases1 = {"bob alice", "alice", "like"};
        printList(textQueries(sentences1, phrases1));
        // 0, 0 2, 0 1

        // testcase 2
        String[] sentences2 = {"jim likes mary", "kate likes tom", "tom does not like jim"};
        String[] phrases2 = {"jim tom", "likes"};
        printList(textQueries(sentences2, phrases2));
        // 2, 0 1

        // testcase 3
        String[] sentences3 = {"how it was done", "are you how", "it goes to", "goes done are it"};
        String[] phrases3 = {"done it", "it"};
        printList(textQueries(sentences3, phrases3));
        // 0 3, 0 2 3

        // testcase 4
        String[] sentences4 = {"it go will away", "go do it", "what to will east"};
        String[] phrases4 = {"it will", "go east will", "will"};
        printList(textQueries(sentences4, phrases4));
        // 0, -1, 0 2

        // testcase 5
        String[] sentences5 = {"bob alice bob alice bob alice"};
        String[] phrases5 = {"bob alice"};
        printList(textQueries(sentences5, phrases5));
        // 0 0 0

        // testcase 6
        String[] sentences6 = {"bob alice bob alice bob alice"};
        String[] phrases6 = {"bob alice bob alice"};
        //printList(textQueries(sentences6, phrases6));
    }

    private static void printList(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
        System.out.println();
    }
}
