public class ASCIIEncodedStrings {
    private static String decode(String input) {
        s = s.reverse();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '1') {
                int num = 0;
                for (int j = 0; j < 3; j++) {
                    num = num * 10 + (int) (s.charAt(j));
                }
                sb.append((char) num);
                i += 3;
            } else {
                for (int j = 0; j < 2; j++) {
                    num = num * 10 + (int) (s.charAt(j));
                }
                sb.append((char) num);
                i += 2;
            }
        }
        return sb.toString();
    }

    // test
    private static String decode(String s) {
        s = new StringBuilder(s).reverse().toString();
        System.out.println("reversed : " + s);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            System.out.print("now check from " + i + " to ");
            if (s.charAt(i) == '1') {
                System.out.print(i + 3 + " , ");
                int num = 0;
                for (int j = i; j < 3; j++) {
                    System.out.print((s.charAt(j) - '0') + ", ");
                    num = num * 10 + (s.charAt(j) - '0');
                }
                System.out.println(", get number: " + num + ", its char is " + (char)num);
                sb.append((char) num);
                i += 3;
            } else {
                System.out.println(i + 2);
                int num = 0;
                for (int j = i; j < 2; j++) {
                    System.out.print((s.charAt(j) - '0') + ", ");
                    num = num * 10 + (s.charAt(j) - '0');
                }
                System.out.println(", get number: " + num + ", its char is " + (char)num);
                sb.append((char) num);
                i += 2;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "729799107101114328297110107";
        System.out.println(decode(s));
    }
}
