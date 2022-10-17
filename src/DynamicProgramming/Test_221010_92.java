package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//가장 높은 탑 쌓기
public class Test_221010_92 {
    static class Brick implements Comparable<Brick>{
        int height;
        int weight;
        int base;

        public Brick(int height, int weight, int base) {
            this.height = height;
            this.weight = weight;
            this.base = base;
        }

        @Override
        public int compareTo(Brick o) {
            return o.base - this.base;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = Integer.MIN_VALUE;
        int[] dis = new int[n];

        ArrayList<Brick> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int base = sc.nextInt();
            int height = sc.nextInt();
            int weight = sc.nextInt();

            arr.add(new Brick(height, weight, base));
        }

        Collections.sort(arr);

        dis[0] = arr.get(0).height;

        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if (arr.get(i).weight < arr.get(j).weight && dis[j] + arr.get(i).height > dis[i]) {
                    dis[i] = dis[j] + arr.get(i).height;
                    if(dis[i] > max) max = dis[i];
                }
            }
            if(dis[i] == 0) dis[i] = arr.get(i).height;
        }

        System.out.println(max);
    }
}
