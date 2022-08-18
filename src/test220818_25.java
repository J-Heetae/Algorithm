import java.util.Scanner;

//Array 큰 수 출력하기
public class test220818_25 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] intArr = new int[n];

        for (int i = 0; i < n; i++) {
            intArr[i] = sc.nextInt();
        }

        System.out.print(intArr[0] + " ");

        for(int i = 1; i< n; i++) {
            if(intArr[i] > intArr[i-1]) {
                System.out.print(intArr[i] + " ");
            }
        }
    }
}
