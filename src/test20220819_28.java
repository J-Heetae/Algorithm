import java.util.*;

//Array 피보나치 수열
public class test20220819_28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] answer = new int[num];

        for (int i = 0; i < num ; i++) {
            if(i < 2) {
                answer[i] = 1;
            } else {
                answer[i] = answer[i-1] + answer[i-2];
            }
        }

        for(int n : answer) {
            System.out.print(n + " ");
        }
    }
}
