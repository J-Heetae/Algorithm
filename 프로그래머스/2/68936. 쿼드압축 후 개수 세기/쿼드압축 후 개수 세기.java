class Solution {
    int[] answer = {0, 0};
    int[][] arr;

    public int[] solution(int[][] arr) {
        this.arr = arr;
        compress(0, arr.length, 0, arr.length);
        return answer;
    }

    void compress(int row1, int row2, int col1, int col2) {
        int startNumber = arr[row1][col1];
        for (int i = row1; i < row2; i++) {
            for (int j = col1; j < col2; j++) {
                if (startNumber != arr[i][j]) {
                    compress(row1, (row1 + row2) / 2, col1, (col1 + col2) / 2);
                    compress((row1 + row2) / 2, row2, col1, (col1 + col2) / 2);
                    compress(row1, (row1 + row2) / 2, (col1 + col2) / 2, col2);
                    compress((row1 + row2) / 2, row2, (col1 + col2) / 2, col2);
                    return;
                }
            }
        }
        answer[arr[row1][col1]]++;
    }
}