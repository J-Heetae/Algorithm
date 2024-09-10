import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String S = br.readLine();
        int i = Integer.parseInt(br.readLine());
        System.out.println(S.charAt(i - 1));
    }
}