package String;

import java.util.ArrayList;
import java.util.List;

public class test220729_5 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

        int solution = solution(board, moves);

        System.out.println("solution = " + solution);
    }
    public static int solution(int[][] board, int[] moves) {

        List<Integer> box = new ArrayList<>();
        int answer = 0;

        for(int m : moves) {
            m = m - 1;
            System.out.println("m = " + m);
            for (int i = 0; i < board.length; i++) {

                System.out.println("board[i][m] = " + board[i][m]);

                if (board[i][m] != 0) {

                    box.add(box.size(), board[i][m]);

                    System.out.println("box = " + box);
//                    System.out.println("box.get(b+ox.size()-2) = " + box.get(box.size() - 2));
//                    System.out.println("box.get(box.size()-1) = " + box.get(box.size() - 1));

                    if (box.get(box.size() - 2) == box.get(box.size() - 1)) {
                        box.remove(box.size() - 2);
                        box.remove(box.size() - 1);
                        answer += 2;
                    }

                    board[i][m] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}
