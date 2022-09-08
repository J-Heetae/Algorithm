package Sorging_Searching;


import java.util.Scanner;

//선택정렬
public class test220908_56 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];

        for(int i=0; i<size; i++) {
            nums[i] = sc.nextInt();
        }

        for(int i=0; i<size-1; i++) {
            int indexMin = i;
            for(int j=i+1; j<size; j++) {
                if(nums[j] < nums[indexMin]) {
                    indexMin = j;
                }
            }
            int tmp = nums[i];
            nums[i] = nums[indexMin];
            nums[indexMin] = tmp;
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
