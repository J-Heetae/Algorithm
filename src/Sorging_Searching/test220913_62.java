package Sorging_Searching;

import java.util.Arrays;
import java.util.Scanner;

//이분검색
public class test220913_62 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int num = sc.nextInt();
        int[] arr = new int[size];

        for(int i=0; i<size; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        System.out.println(binarySearch(arr, num));

    }

    static int binarySearch(int[] arr, int num) {
        int result = -1;
        int lt = 0, rt = arr.length-1;

        while(lt <= rt) {
            int center = rt - lt;

            if(arr[center] > num) {
                rt = center-1;
            } else if(arr[center] < num) {
                lt = center+1;
            } else {
                result = center;
                break;
            }
        }

        return result + 1;
    }
}
