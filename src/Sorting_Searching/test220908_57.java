package Sorting_Searching;

import java.util.Scanner;

public class test220908_57 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];

        for(int i=0; i<size; i++) {
            nums[i] = sc.nextInt();
        }

        for(int i=0; i<size; i++) {
            for(int j=1; j<size-i; j++) {
                if(nums[j-1] > nums[j]) {
                    int tmp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
