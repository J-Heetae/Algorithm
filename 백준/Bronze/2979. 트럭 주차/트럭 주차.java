import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] fees = new int[4];
        for (int i = 1; i <= 3; i++) {
            fees[i] = sc.nextInt();
        }

        int[] prefix = new int[101];
        for (int i = 0; i < 3; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            prefix[from]++;
            prefix[to]--;
        }

        int fee = 0;
        for (int i = 1; i <= 100; i++) {
            prefix[i] += prefix[i - 1];
            fee += prefix[i] * fees[prefix[i]];
        }

        System.out.println(fee);
    }
}