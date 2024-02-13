class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1)
            return sticker[0];
        return Math.max(getMax(sticker.length - 1, true, sticker), getMax(sticker.length, false, sticker));
    }

    private int getMax(int to, boolean firstUsed, int[] sticker) {
        int[] dp = new int[sticker.length];

        if (firstUsed) {
            dp[0] = dp[1] = sticker[0];
        } else {
            dp[0] = 0;
            dp[1] = sticker[1];
        }

        for (int i = 2; i < to; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        return dp[to - 1];
    }
}