public class ASCIIEncodedStrings {
    private static String decode(String s) {
        s = new StringBuilder(s).reverse().toString();
        // System.out.println("reversed : " + s);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            // System.out.print("now check from " + i + " to ");
            if (s.charAt(i) == '1') {
                sb.append((char) getNum(s, i, 3));
                i += 3;
            } else {
                sb.append((char) getNum(s, i, 2));
                i += 2;
            }
        }
        return sb.toString();
    }
    private static int getNum(String s, int start, int steps) {
        int num = 0;
        // System.out.print(start + count + " : ");
        for (int i = 0; i < steps; i++) {
            // System.out.print((s.charAt(i + i) - '0') + ", ");
            num = num * 10 + (s.charAt(start + i) - '0');
        }
        // System.out.println(", get number: " + num + ", its char is " + (char)num);
        return num;
    }

    public static void main(String[] args) {
        String s = "701011792823411101701997927";
        System.out.println(decode(s));
    }
}
