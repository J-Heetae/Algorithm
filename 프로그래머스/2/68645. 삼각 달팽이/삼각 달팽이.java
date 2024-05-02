import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(4);
    }

    //아래, 오른쪽, 왼쪽 위
    final int[] DX = {1, 0, -1};
    final int[] DY = {0, 1, -1};

    int[][] snail;
    int length;

    public int[] solution(int n) {
        snail = new int[n][n];
        length = n;
        // fillSnail(1, 0, 0, 0);

        int number = 1;
        int x = 0;
        int y = 0;
        int dir = 0;
        while (true) {
            snail[x][y] = number++;
            int cnt = 0;

            for (int i = 0; i < 2; i++) {
                int nx = x + DX[(dir + i) % 3];
                int ny = y + DY[(dir + i) % 3];

                if (nx >= length || ny >= length || snail[nx][ny] != 0) {
                    cnt++;
                    continue;
                }
                dir = (dir + i) % 3;
                x = nx;
                y = ny;
                break;
            }
            if (cnt == 2) {
                break;
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (snail[i][j] == 0)
                    break;
                answer.add(snail[i][j]);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    void fillSnail(int number, int dir, int x, int y) {
        snail[x][y] = number;

        for (int i = 0; i < 2; i++) {
            int nx = x + DX[(dir + i) % 3];
            int ny = y + DY[(dir + i) % 3];

            if (nx >= length || ny >= length || snail[nx][ny] != 0) {
                continue;
            }

            fillSnail(number + 1, (dir + i) % 3, nx, ny);
            break;
        }
    }
}