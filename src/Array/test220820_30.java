package Array;

import java.util.*;

//Array 뒤집은 소수
public class test220820_30 {
    public boolean isPrime(int num) {
        if(num == 1) return false;
        for(int i=2; i<num; i++){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public ArrayList<Integer> solution(int n, int[] arr){
        ArrayList<Integer> answer = new ArrayList<>();

        for(int a : arr) {
            int rev = 0;
            while(a!=0) {
                rev = rev * 10 + a % 10;
                a /= 10;
            }
            if(isPrime(rev)) answer.add(rev);
        }

        return answer;
    }

    public static void main(String[] args) {
        test220820_30 t = new test220820_30();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int a : t.solution(n, arr)) {
            System.out.print(a + " ");
        }
    }
}
