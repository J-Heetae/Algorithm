import java.util.Scanner;

//Array 가위바위보
public class test20220819_27 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int[] a = new int[num];
        int[] b = new int[num];

        for (int i = 0; i < num ; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < num ; i++) {
            b[i] = sc.nextInt();
        }

        for (int i = 0; i < num; i++) {
            if (((a[i] > b[i]) && (a[i] - b[i] == 1)) || (a[i] == 1 && b[i] == 3)) {
                System.out.println("A");
            } else if (a[i] == b[i]) {
                System.out.println("D");
            } else {
                System.out.println("B");
            }
        }
    }
}