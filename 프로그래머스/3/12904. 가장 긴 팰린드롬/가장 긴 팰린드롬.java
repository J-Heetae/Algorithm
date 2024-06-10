class Solution {
    public int solution(String s) {
        int size = s.length();
        int maxLength = 1;

        boolean[][] dp = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < size - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
            }
        }

        for (int length = 2; length <= size; length++) {
            for (int i = 0; i < size - length + 1; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    maxLength = length;
                }
            }
        }
        return maxLength;
    }
}