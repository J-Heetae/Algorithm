package Sorting_Searching;

import java.util.Scanner;

public class test220908_58 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];

        for(int i=0; i<size; i++) {
            nums[i] = sc.nextInt();
        }

        for(int i=1; i<size; i++) {
            int tmp = nums[i];
            int prev = i -1;
            while((prev >=0) && (nums[prev] > tmp)) {
                nums[prev + 1] = nums[prev];
                prev--;
            }
            nums[prev+1] = tmp;
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
