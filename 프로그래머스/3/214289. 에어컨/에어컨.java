import java.util.Arrays;

class Solution {
    static int INF = Integer.MAX_VALUE;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        t1 += 10;
        t2 += 10;
        temperature += 10;
        int[][] dp = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][temperature] = 0;
        for (int time = 1; time < dp.length; time++) {
            int from = (onboard[time] == 1) ? t1 : 0;
            int to = (onboard[time] == 1) ? t2 : 50;
            for (int temp = from; temp <= to; temp++) { //쾌적한 온도 범위만
                if (temp != 0 && dp[time - 1][temp - 1] != INF) {
                    if (temp - 1 >= temperature) { //이전 온도가 외부 온도보다 높거나 같을때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1] + a);
                    } else { //이전 온도가 외부 온도보다 낮을때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp - 1]);
                    }
                }

                if (dp[time - 1][temp] != INF) {
                    if (temp == temperature) { //이전 온도가 외부 온도랑 같을 때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp]);
                    } else { //이전 온도가 외부 온도랑 다를 때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp] + b);
                    }
                }

                if (temp != 50 && dp[time - 1][temp + 1] != INF) {
                    if (temp + 1 <= temperature) { //이전 온도가 외부 온도보다 낮거나 같을때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp + 1] + a);
                    } else { //이전 온도가 외부 온도보다 높을 때
                        dp[time][temp] = Math.min(dp[time][temp], dp[time - 1][temp + 1]);
                    }
                }
            }
        }
        int minEnergy = Integer.MAX_VALUE;
        for (int i = 0; i <= 50; i++) {
            if (dp[onboard.length - 1][i] != INF) {
                minEnergy = Math.min(minEnergy, dp[onboard.length - 1][i]);
            }
        }
        return minEnergy;
    }
}