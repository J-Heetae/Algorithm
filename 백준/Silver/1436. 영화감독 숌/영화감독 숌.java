import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int num = 666;
        for(int i=2; i<=N; i++) {
            num++;
            while (!String.valueOf(num).contains("666")) {
                num++;
            }
        }
        System.out.println(num);
    }
}