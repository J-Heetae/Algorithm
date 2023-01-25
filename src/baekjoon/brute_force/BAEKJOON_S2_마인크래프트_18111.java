package baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BAEKJOON_S2_마인크래프트_18111 {
    static int N;
    static int M;
    static int B;
    static int min = Integer.MAX_VALUE;
    static int height = Integer.MIN_VALUE;

    static Comparator<Integer> comparator = Comparator.reverseOrder();
    static SortedMap<Integer, Integer> map = new TreeMap<>(comparator);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        B = Integer.parseInt(s[2]);

        int freqKey = Integer.MIN_VALUE;
        int freqValue = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            s = br.readLine().split(" ");

            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(s[j]);

                map.put(num, map.getOrDefault(num, 0) +1);

                if(map.get(num) > freqValue) {
                    freqKey = num;
                    freqValue = map.get(num);
                }
            }
        }

        narasi(freqKey);

        for(int i=map.firstKey(); i>=map.lastKey(); i--) {
            narasi(i);
        }

        System.out.println(min + " " + height);
    }

    private static void narasi(int num) {
        int time = 0;
        int block = B;

        for(Integer key : map.keySet()) {
            if(time > min) return;

            int gap = num - key;
            int cnt = map.get(key);

            if(gap > 0) {
                if(block < gap * cnt)
                    return;

                block -= gap * cnt;
                time += gap * cnt;
                continue;
            }

            if(gap < 0) {
                block += Math.abs(gap) * cnt;
                time += 2 * Math.abs(gap) * cnt;
            }
        }

        if(min == time) {
            if(height < num) height = num;
        }

        if(min > time) {
            min = time;
            height = num;
        }
    }
}