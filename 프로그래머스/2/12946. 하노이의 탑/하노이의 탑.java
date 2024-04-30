import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int idx;
    ArrayList<int[]> answer;

    public int[][] solution(int n) {
        answer = new ArrayList<>();
        hanoi(n, 1, 2, 3);

        int[][] arr = new int[answer.size()][2];
        for (int i = 0; i < answer.size(); i++)
            arr[i] = answer.get(i);
        return arr;
    }

    void hanoi(int n, int start, int mid, int to) {
        if (n == 1) {
            answer.add(new int[] {start, to});
            return;
        }
        hanoi(n - 1, start, to, mid);
        answer.add(new int[] {start, to});
        hanoi(n - 1, mid, start, to);
    }
}