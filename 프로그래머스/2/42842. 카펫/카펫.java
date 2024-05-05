import javax.security.sasl.SaslServer;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(10, 2);
    }
    public int[] solution(int brown, int yellow) {
        int n = brown + yellow;
        int col = 3;
        int row = n / 3;
        while (row >= col) {
            int brownNeed = row * 2 + (col - 2) * 2;
            int yellowNeed = (row - 2) * (col - 2);
            if (brown == brownNeed && yellow == yellowNeed)
                break;

            row--;
            col++;
            while(row * col != n) {
                if(row * col > n) {
                    row--;
                } else {
                    col++;
                }
            }
        }
        return new int[]{row, col};
    }
}