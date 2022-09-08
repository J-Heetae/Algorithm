package Sorging_Searching;

import java.util.Scanner;

//Least Recently Used
public class test220908_59 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cacheSize = sc.nextInt();
        int n = sc.nextInt();
        int[] cache = new int[cacheSize];

        for(int i=0; i<n; i++) {
            int tmp = sc.nextInt();
            int index = isContains(tmp, cache);

            if (index == -1) index = cacheSize - 1;

            while(index > 0) {
                cache[index] = cache[index-1];
                index--;
            }
            cache[0] = tmp;
        }

        for (int c : cache) System.out.print(c + " ");
    }

    // 반복문으로 작업이 캐시 메모리에 존재하는지 확인하고 index반환 없으면 index = -1;
    static int isContains(int num, int[] arr) {
        int index = -1;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] == num) {
                index = i;
                break;
            }
        }
        return index;
    }
}
