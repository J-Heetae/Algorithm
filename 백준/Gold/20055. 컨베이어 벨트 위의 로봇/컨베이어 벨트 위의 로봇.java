import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] read = br.readLine().split(" ");
        int n = Integer.parseInt(read[0]);
        int k = Integer.parseInt(read[1]);

        int[] conveyor = new int[n * 2];
        boolean[] robots = new boolean[n];

        read = br.readLine().split(" ");
        for(int i=0; i<conveyor.length; i++) {
            conveyor[i] = Integer.parseInt(read[i]);
        }

        int step = 0;

        while (true) {
            step++;

            //컨베이어 벨트 회전
            int tmp = conveyor[n*2-1];
            for(int i=n*2-1; i>0; i--) {
                conveyor[i] = conveyor[i-1];
            }
            conveyor[0] = tmp;

            //로봇도 벨트랑 같이 이동
            for(int i=n-1; i>0; i--) {
                robots[i] = robots[i-1];
            }
            robots[0] = false;
            robots[n-1] = false;

            //로봇 스스로 이동
            for(int i=n-1; i>0; i--) {
                if(robots[i-1] && !robots[i] && conveyor[i]>0) {
                    robots[i-1] = false;
                    robots[i] = true;
                    conveyor[i]--;
                    robots[n-1] = false;
                }
            }

            //로봇 올리기
            if(conveyor[0] > 0) {
                robots[0] = true;
                conveyor[0]--;
            }

            //내구도가 0인 벨트 조회
            int cnt = 0;
            for(int i=0; i<2*n; i++) {
                if(conveyor[i] == 0) {
                    cnt++;
                }
            }
            if(cnt >= k){
                break;
            }
        }
        System.out.println(step);
    }
}