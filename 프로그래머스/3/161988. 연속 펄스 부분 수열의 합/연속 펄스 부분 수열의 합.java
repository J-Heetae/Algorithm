class Solution {
    public long solution(int[] sequence) {
        int length = sequence.length;
        long[] dp1 = new long[length+1];
        long[] dp2 = new long[length+1];

        for(int i=1; i<=length; i++) {
            if (i % 2 == 0) {
                dp1[i] = sequence[i-1] * -1;
                dp2[i] = sequence[i-1];
            } else {
                dp2[i] = sequence[i-1] * -1;
                dp1[i] = sequence[i-1];
            }
        }

        long answer = 0;
        for(int i=1; i<=length; i++) {
            dp1[i] = Math.max(dp1[i - 1] + dp1[i], dp1[i]);
            dp2[i] = Math.max(dp2[i - 1] + dp2[i], dp2[i]);
            answer = Math.max(answer, dp1[i]);
            answer = Math.max(answer, dp2[i]);
        }
        return answer;
    }
}