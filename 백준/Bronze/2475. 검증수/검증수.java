import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;
        for(int i = 0; i < 5; i++) {
            result += (int)Math.pow(Integer.parseInt(st.nextToken()),2);
            result %= 10;
        }
        System.out.println(result);
    }
}