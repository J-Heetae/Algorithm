import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= N / 3; i++) {
            for (int j = 0; j <= N / 5; j++) {
                if (3 * i + 5 * j == N) {
                    answer = Math.min(answer, i + j);
                }
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}