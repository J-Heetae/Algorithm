package baekjoon.sort;

import java.io.*;
import java.util.*;

public class BAEKJOON_통계학_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] arr = new int[N];
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
        }

        Arrays.sort(arr);

        int most = 0;
        int maxCnt = 0;
        ArrayList<Integer> maxArr =new ArrayList<>();
        for(Integer key : hm.keySet()) {
            if(hm.get(key) > maxCnt) {
                maxCnt = hm.get(key);
                maxArr.clear();
                maxArr.add(key);
            } else if(hm.get(key) == maxCnt) {
                maxArr.add(key);
            }
        }

        Collections.sort(maxArr);

        if(maxArr.size() == 1)
            most = maxArr.get(0);
        else
            most = maxArr.get(1);


        bw.write(Math.round((double)sum/N) + "\n");
        bw.write(arr[N / 2] + "\n");
        bw.write(most + "\n");
        bw.write(max-min + "\n");


        bw.flush();
        bw.close();
        br.close();
    }
}