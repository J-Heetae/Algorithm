import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        StringBuilder ROT13 = new StringBuilder();
        int a = 'a';
        int z = 'z';

        int A = 'A';
        int Z = 'Z';

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c >= a && c <= z) {
                c += 13;
                if (c > z) {
                    int dif = c - z;
                    c = (char) (a + dif - 1);
                }
                ROT13.append(c);
                continue;
            }

            if (c >= A && c <= Z) {
                c += 13;
                if (c > Z) {
                    int dif = c - Z;
                    c = (char) (A + dif - 1);
                }
                ROT13.append(c);
                continue;
            }
            ROT13.append(c);
        }
        System.out.println(ROT13);
    }
}