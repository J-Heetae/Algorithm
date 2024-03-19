class Solution {

    private static final int ATTACK = 1;
    static int row, col;
    static int[][] prefix;

    public int solution(int[][] board, int[][] skill) {
        row = board.length;
        col = board[0].length;
        prefix = new int[row + 1][col + 1];

        for (int[] one : skill) {
            if (one[0] == ATTACK) {
                one[5] *= -1;
            }
            prefix[one[1]][one[2]] += one[5];
            prefix[one[1]][one[4] + 1] += one[5] * -1;
            prefix[one[3] + 1][one[2]] += one[5] * -1;
            prefix[one[3] + 1][one[4] + 1] += one[5];
        }

        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] += prefix[i][j - 1];
            }
        }

        for (int i = 0; i < col; i++) {
            for (int j = 1; j <= row; j++) {
                prefix[j][i] += prefix[j - 1][i];
            }
        }

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] += prefix[i][j];
                if (board[i][j] > 0)
                    count++;
            }
        }
        return count;
    }
}