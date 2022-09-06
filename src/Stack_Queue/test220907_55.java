package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//응급실
public class test220907_55 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int target = sc.nextInt();
        int answer = 1;

        // 접수순서와 위험도를 객체로 만들어 Queue에 offer
        Queue<Patient> que = new LinkedList<>();
        for(int i=0; i<size; i++) que.offer(new Patient(i, sc.nextInt()));

        // Queue에 마지막 하나가 남거나 break 할때까지 돌림
        while(que.size() > 1) {
            // Queue의 첫번째 값을 tmp에 저장
            Patient tmp = que.poll();
            // 첫번째 환자의 위험도보다 높은 환자가 있는지 확인
            for(Patient p : que) {
                // 만약 있다면 tmp에 null을 넣고 break
                if(p.priority > tmp.priority) {
                    que.offer(tmp);
                    tmp = null;
                    break;
                }
            }
            // tmp가 null이 아니라면(위험도가 더 높은 환자가 없다면) target 환자인지 확인,
            // target 환자가 아니면 answer(순서)++
            if(tmp != null) {
                if(tmp.id == target) break;
                else answer++;
            }
        }

        System.out.println(answer);
    }

    static class Patient {
        int id;
        int priority;

        public Patient(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }

}

