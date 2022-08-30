package HashMap_TreeSet;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

//K번째 큰수
public class test220830_47 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int k = sc.nextInt();
        int answer = 0;
        int[] arr = new int[length];
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for(int i=0; i<length; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                for(int l=j+1; l<length; l++) {
                    set.add(arr[i] + arr[j] + arr[l]);
                }
            }
        }

        int cnt = 0;

        for(int s : set) {
            cnt++;
            if(cnt == k)  answer = s;
        }

        if(answer == 0) System.out.print("-1");
        else System.out.print(answer);
    }
}
