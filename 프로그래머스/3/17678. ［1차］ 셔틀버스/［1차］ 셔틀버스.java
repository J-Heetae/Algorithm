import java.util.Arrays;


class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"});
    }

    public String solution(int n, int t, int m, String[] timetable) {
        int[] timeArr = new int[60 * 24];
        for (String s : timetable) {
            int hour = Integer.parseInt(s.substring(0, 2));
            int min = Integer.parseInt(s.substring(3));
            timeArr[(hour * 60) + min]++;
        }

        int[] arrivalArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arrivalArr[i] = (9 * 60) + (t * (i - 1));
        }

        int startTime = 0;
        int answer = arrivalArr[n];
        outer:
        for (int i = 0; i < n; i++) {
            int start = startTime;
            int end = arrivalArr[i + 1];

            int cnt = m;
            for (int j = start; j <= end; j++) {
                if (timeArr[j] == 0)
                    continue;

                int minus = Math.min(timeArr[j], cnt);
                cnt -= minus;
                timeArr[j] -= minus;

                if (cnt == 0) {
                    if (i == n - 1) {
                        answer = j - 1;
                        break outer;
                    }
                    startTime = j;
                    continue outer;
                }
            }
        }
        return String.format("%02d", answer / 60) + ":" + String.format("%02d", answer % 60);
    }
}