import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            
            int[] edges = new int[3];
            for(int i=0; i<3; i++) {
                edges[i] = Integer.parseInt(st.nextToken());
            }
            
            if(edges[0] == 0) {
                break;
            }
            
            Arrays.sort(edges);
            
            if(Math.pow(edges[2],2) == (Math.pow(edges[0],2) + Math.pow(edges[1],2))) {
                sb.append("right\n");
            } else {
                sb.append("wrong\n");
            }
        }
        System.out.println(sb);
    }
}