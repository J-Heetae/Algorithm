package baekjoon.sort;

import java.io.*;

//계수정렬
public class BAEKJOON_수정렬하기3_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        int max = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            a[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, a[i]);
        }

        int[] idx = new int[max+1];
        int[] cnt = new int[max+1];
        int sum = 0;

        for(int i=0; i<N; i++) {
            idx[a[i]]++;
            sum++;
        }

        int[] sorted = new int[N];

        for(int i=max; i>=1; i--) {
            if(idx[i] == 0) continue;

            while(idx[i] > 0) {
                sorted[sum-1] = i;
                sum--;
                idx[i]--;
            }
        }

        for(int i=0; i< sorted.length; i++) {
            bw.write(sorted[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}