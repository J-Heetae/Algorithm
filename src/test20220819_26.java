import java.util.*;

//Array 보이는 학생
class test20220819_26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        int answer = 1;
        int cnt = 0;

        while(sc.hasNext()){
            arr[cnt] = sc.nextInt();
            cnt++;
        }

        int maxNum = arr[0];

        for(int i=1; i<num; i++) {
            if(arr[i] > maxNum ) {
                maxNum = arr[i];
                answer++;
            }
        }

        System.out.print(answer);
    }
}
