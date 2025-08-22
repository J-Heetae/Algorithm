import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        int[][] boxes = new int[M][3];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            boxes[i] = new int[] {
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };
        }
        Arrays.sort(boxes, (a, b) -> {
            if(a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int answer = 0;
        int[] weights = new int[N];
        for(int[] box : boxes) {
            int from = box[0];
            int to = box[1];
            int weight = box[2];

            int possibleWeight = Integer.MAX_VALUE;
            for(int i=from; i<to; i++) {
                possibleWeight = Math.min(possibleWeight, C - weights[i]);
            }
            for(int i=from; i<to; i++) {
                weights[i] += Math.min(weight, possibleWeight);
            }
            answer += Math.min(weight, possibleWeight);
        }

        System.out.print(answer);
    }
}