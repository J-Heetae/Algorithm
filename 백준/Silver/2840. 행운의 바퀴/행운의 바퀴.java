import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] wheel = new char[N];
        Arrays.fill(wheel, '?');

        Set<Character> set = new HashSet<>();
        boolean flag = false;
        int idx = 0;
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            char alphabet = st.nextToken().charAt(0);
            idx = (idx + count) % N;
            if(wheel[idx] != '?' && wheel[idx] != alphabet) {
                flag = true;
                break;
            }
            if(wheel[idx] == '?' && set.contains(alphabet)) {
                flag = true;
                break;
            }
            set.add(alphabet);
            wheel[idx] = alphabet;
        }

        if(flag) {
            System.out.println("!");
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<N; i++) {
                sb.append(wheel[(N + idx - i) % N]);
            }
            System.out.println(sb);
        }
    }
}