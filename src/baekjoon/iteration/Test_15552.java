package baekjoon.iteration;

import java.io.*;
import java.util.StringTokenizer;

public class Test_15552 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int num = Integer.parseInt(br.readLine());

        for(int i=0; i<num; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(a + b + "\n");
        }
        bw.close();
    }
}
