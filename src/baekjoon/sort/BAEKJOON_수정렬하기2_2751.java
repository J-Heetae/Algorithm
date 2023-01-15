package baekjoon.sort;

import java.io.*;

//병합정렬
public class BAEKJOON_수정렬하기2_2751 {

    static int[] sorted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        sorted = new int[N];


        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(nums, 0,N-1);

        for(int i=0; i<N; i++) {
            bw.write(nums[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void mergeSort(int[] nums, int i, int j) {
        if(i >= j) return;

        int m = i + (j-i)/2;

        mergeSort(nums, i, m);
        mergeSort(nums, m+1, j);
        merge(nums, i, m , j);
    }

    static void merge(int[] nums, int i, int m, int j) {
        int left = i;
        int right = m+1;
        int k = i;

        while(left <= m && right <= j) {
            if(nums[left] <= nums[right]) {
                sorted[k] = nums[left];
                left++;
            } else {
                sorted[k] = nums[right];
                right++;
            }

            k++;
        }

        if(left > m) {
            for(int idx=right; idx<=j; idx++) {
                sorted[k] = nums[idx];
                k++;
            }
        }

        if(right > j) {
            for(int idx=left; idx<=m; idx++) {
                sorted[k] = nums[idx];
                k++;
            }
        }

        for(int idx=i; idx<=j; idx++) {
            nums[idx] = sorted[idx];
        }
    }
}