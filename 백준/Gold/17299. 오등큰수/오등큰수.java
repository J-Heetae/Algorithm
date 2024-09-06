import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] count = new int[1_000_001];
        int[] answer = new int[N];
        Arrays.fill(answer, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            count[num]++;
        }

        Stack<Integer> idxStack = new Stack<>();
        for(int i=0; i<N; i++) {
            while(!idxStack.isEmpty()) {
                if(count[arr[i]] > count[arr[idxStack.peek()]]) {
                    int pop = idxStack.pop();
                    answer[pop] = arr[i];
                } else {
                    break;
                }
            }
            idxStack.push(i);
        }

        for(int i=0; i<N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
