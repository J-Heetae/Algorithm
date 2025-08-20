import java.util.*;

class Main {
    static char[] mustHave = {'a', 'e', 'i', 'o', 'u'};

    static int L, C;
    static char[] alphabets;
    static boolean[] used;
    static char[] picked;
    static Set<String> passwords = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();
        alphabets = new char[C];
        used = new boolean[C];
        picked = new char[L];

        sc.nextLine();
        String[] split = sc.nextLine().split(" ");
        for (int i = 0; i < C; i++) {
            alphabets[i] = split[i].charAt(0);
        }

        makePassword(0);

        for (String s : passwords) {
            System.out.println(s);
        }
    }

    static void makePassword(int idx) {
        if (idx == L) {
            StringBuilder curr = new StringBuilder();
            for (int i = 0; i < L; i++) {
                curr.append(picked[i]);
            }
            int mustHaveCount = 0;
            for (char c : mustHave) {
                if (curr.indexOf(c + "") != -1) {
                    mustHaveCount++;
                }
            }

            if (mustHaveCount >= 1 && mustHaveCount <= L - 2) {
                passwords.add(curr.toString());
            }
            return;
        }

        for (int i = 0; i < C; i++) {
            if (used[i]) {
                continue;
            }
            if (idx != 0 && picked[idx - 1] - 'a' >= alphabets[i] - 'a') {
                continue;
            }
            used[i] = true;
            picked[idx] = alphabets[i];
            makePassword(idx + 1);
            used[i] = false;
        }
    }
}