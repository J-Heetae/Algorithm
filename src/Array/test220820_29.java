package Array;

import java.util.*;

//Array 소수 (에라토스테네스 체)
public class test220820_29 {
    //이중 for문 돌리면 시간이 너무 오래걸림
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        ArrayList<Integer> prime = new ArrayList<>();
        prime.add(2);
        boolean flag = true;
        int answer = 0;

        for(int i=0; i<arr.length; i++) {
            arr[i] = i+1;
        }

        for(int i=1; i< arr.length; i++){
            flag = true;
            for(int p : prime) {
                if(arr[i] % p == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag == true) {
                answer++;
                prime.add(arr[i]);
            }
        }
        System.out.print(prime.size());
    }*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int answer = 0;
        int[] arr = new int[num + 1];

        for (int i = 2; i < num + 1; i++) {
            if (arr[i] == 0) {
                answer++;
                for (int j = i; j <= num; j += i) arr[j] = 1;
            }
        }

        System.out.print(answer);
    }
}
