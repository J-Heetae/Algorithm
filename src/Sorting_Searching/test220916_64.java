package Sorting_Searching;

import java.util.Arrays;
import java.util.Scanner;

public class test220916_64 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int horse = sc.nextInt();
        int answer = 0;
        int[] stable = new int[size];

        for(int i=0; i<size; i++) {
            stable[i] = sc.nextInt();
        }

        Arrays.sort(stable);

        int lt = 1, rt = stable[size-1];

        while(lt <= rt) {
            int center = (lt + rt) / 2;
            if(count(stable, center) >= horse) {
                lt = center + 1;
                answer = center;
            } else {
                rt = center -1;
            }
        }

        System.out.println(answer);

    }

    static int count(int[] stable, int center) {
        int count = 1;
        int lt = stable[0];
        for(int i=1; i<stable.length; i++) {
            if(stable[i] - lt >= center) {
                lt = stable[i];
                count++;
            }
        }
        return count;
    }
}
