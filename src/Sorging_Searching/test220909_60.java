package Sorging_Searching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//중복확인
public class test220909_60 {

    // Array.sort 사용해서 정렬 후 중복 확인
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        int[] nums = new int[size];

        for(int i=0; i<size; i++) nums[i] = sc.nextInt();

        Arrays.sort(nums);

        String answer = isDuplicateInSortedArray(nums);

        System.out.println(answer);
    }

    static String isDuplicateInSortedArray(int[] arr) {
        String answer = "U";

        for(int i=0; i<arr.length-2; i++) {
            if (arr[i] == arr[i + 1]) {
                answer = "D";
                break;
            }
        }

        return answer;
    }


    /* HashMap 사용해서 중복 확인

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        int[] nums = new int[size];
        HashMap<Integer, Integer> hm = new HashMap<>();
        String answer = "U";

        for(int i=0; i<size; i++) nums[i] = sc.nextInt();

        for(int i=0; i<size; i++) {
            if(hm.containsKey(nums[i])) {
                answer = "D";
                break;
            } else {
                hm.put(nums[i], 1);
            }
        }

        System.out.print(answer);
    }
    */

    /* 퀵정렬 사용 후 중복확인

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        int[] nums = new int[size];

        for(int i=0; i<size; i++) nums[i] = sc.nextInt();

        leftQuickSort(nums, 0, size - 1);

        if(isDuplicateInSortedArray(nums)) System.out.print("D");
        else System.out.print("U");

    }

    static boolean isDuplicateInSortedArray(int[] arr) {
        boolean flag = false;

        for(int i=0; i<arr.length-2; i++) {
            if (arr[i] == arr[i + 1]) {
            flag = true;
            break;
            }
        }

        return flag;
    }

    static void leftQuickSort(int[] arr, int l, int r) {
        if(l >= r) return;

        int pivot = partition(arr, l, r);

        leftQuickSort(arr, l, pivot-1);
        leftQuickSort(arr, pivot+1, r);
    }

    static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int lo = l;
        int hi = r;

        while(lo < hi) {
            while((arr[hi] > pivot) && (hi > lo)) hi--;
            while((arr[lo] <= pivot) && (hi > lo)) lo++;
            swap(arr, lo, hi);
        }

        swap(arr, l, lo);

        return lo;
    }

    static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
    */
}
