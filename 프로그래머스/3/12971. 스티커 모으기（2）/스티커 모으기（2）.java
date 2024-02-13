class Solution {
    public int solution(int sticker[]) {
        int length = sticker.length;

        if(length == 1) {
            return sticker[0];
        } else if (length == 2) {
            return Math.max(sticker[0], sticker[1]);
        }

        int answer;

        int[] dp = new int[length];

        //첫번째 스티커 사용
        dp[0] = sticker[0];
        dp[1] = dp[0];
        dp[2] = dp[1] + sticker[2];
        int a, b;
        for (int i = 3; i < length - 1; i++) {
            a = dp[i - 1];
            b = dp[i - 2] + sticker[i];
            dp[i] = Math.max(a, b);
        }

        answer = Math.max(dp[length - 2], dp[length - 3]);

        //첫번째 스티커 미사용
        dp[0] = 0;
        dp[1] = dp[0] + sticker[1];
        for (int i = 2; i < length; i++) {
            a = dp[i - 1];
            b = dp[i - 2] + sticker[i];
            dp[i] = Math.max(a, b);
        }

        answer = Math.max(answer, dp[length - 2]);
        return Math.max(answer, dp[length - 1]);
    }
}