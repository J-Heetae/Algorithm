package baekjoon.linkedList;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class BAEKJOON_S2_키로거_5397 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for(int tc=0; tc<T; tc++) {
            String password = sc.nextLine();
            int length = password.length();

            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iter = list.listIterator();

            for(int i=0; i<length; i++) {
                Character c = password.charAt(i);

                if(c == '<') {
                    if(iter.hasPrevious())
                        iter.previous();
                    continue;
                }
                if(c == '>') {
                    if(iter.hasNext())
                        iter.next();
                    continue;
                }
                if(c == '-') {
                    if(iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                    continue;
                }
                iter.add(c);
            }

            StringBuilder sb = new StringBuilder();

            for(Character c : list)
                sb.append(c);

            System.out.println(sb);
        }
    }
}

// 맞게 작성한거 같은데 자꾸 틀리게 나오길래
// "만약 커서의 위치가 줄의 마지막이 아니라면, 커서 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동한다."
// 이 구문에 꽂혀서 한참 틀리다가
// 알고보니 처음에 BufferedWriter 를 사용했는데 \n (줄바꿈)을 추가해주지 않아서 틀린거였네요.