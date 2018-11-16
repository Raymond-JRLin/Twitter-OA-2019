public class Parenthesis {
    private static String[] braces(String[] inputs) {
        int n = inputs.length;
        String[] result = new String[n];

        for (int i = 0; i < n; i++) {
            if (isValid(inputs[i])) {
                result[i] = "YES";
            } else {
                result[i] = "NO";
            }
        }

        return result;
    }
    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if ( c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] input = new String[]{"{}[]()", "{[}]}"}; // YES NO
        for (String s : braces(input)) {
            System.out.println(s);
        }
    }
}
