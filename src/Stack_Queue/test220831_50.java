package Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

//크레인 인형뽑기(카카오)
public class test220831_50 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        //인형뽑기 박스 사이즈 n*n
        int n = sc.nextInt();
        //인형뽑기 박스
        int[][] box = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                box[i][j] = sc.nextInt();
            }
        }
        //뽑기 횟수
        int count = sc.nextInt();
        //뽑는 위치 배열
        int[] idxArr = new int[count];
        for(int i=0; i<count; i++) {
            idxArr[i] = sc.nextInt() -1;
        }
        //stack 생성
        Stack<Integer> stack = new Stack<>();

        for(int idx : idxArr) {
            for (int i = 0; i < n; i++) {
                // 인형 찾기
                if (box[i][idx] != 0) {
                    //인형 있던 자리 0으로 초기화
                    int tmp = box[i][idx];
                    box[i][idx] = 0;
                    //뽑아놓은 인형이 있나 확인
                    if(!stack.isEmpty()) {
                        //뽑은 인형과 같은지 확인
                        if(stack.peek() == tmp) {
                            //같으면 인형 터트리고 터트린 인형 개수 추가
                            stack.pop();
                            answer += 2;
                            break;
                        }
                    }
                    // 뽑은 인형이 없는 경우, 뽑은 인형이 다른 경우 stack에 추가
                    stack.push(tmp);
                    break;
                }
            }
        }
        System.out.print(answer);
    }
}
