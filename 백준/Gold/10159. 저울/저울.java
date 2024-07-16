import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = extractInt();
        int M = extractInt();

        boolean[][] arr = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i][i] = true;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A][B] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(arr[i][j] || arr[j][i]) {
                    arr[i][j] = true;
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (!arr[i][j])
                    count++;
            }
            answer.append(count).append("\n");
        }
        System.out.println(answer);
    }

    static int extractInt() throws IOException {
        st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
}