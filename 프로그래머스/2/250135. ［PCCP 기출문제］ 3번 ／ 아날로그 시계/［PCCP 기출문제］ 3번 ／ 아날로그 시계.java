class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(11, 58, 59, 11, 59, 0);
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        final int LENGTH = 60 * 60 * 12;
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;

        int totalTime = endTime - startTime;

        int secHandStatPos = s1 * 60 * 12;
        int minHandStatPos = (12 * (m1 * 60 + s1)) % LENGTH;
        int hourHandStatPos = (60 * (h1 * 60 + m1) + s1) % LENGTH;

        for (int i = 0; i < totalTime; i++) {
            int startHour = hourHandStatPos;
            int endHour = (hourHandStatPos + 1) % LENGTH;
            hourHandStatPos = (hourHandStatPos + 1) % LENGTH;

            int startMin = minHandStatPos;
            int endMin = (minHandStatPos + 12) % LENGTH;
            minHandStatPos = (minHandStatPos + 12) % LENGTH;

            int startSec = secHandStatPos;
            int endSec = (secHandStatPos + 720) % LENGTH;
            secHandStatPos = (secHandStatPos + 720) % LENGTH;

            if (startSec == startHour || startHour == startMin) {
                answer++;
                continue;
            }

            if(startSec > endSec && startHour < endHour) {
                if (startSec <= startHour)
                    answer++;
                if(endSec > endHour)
                    answer++;
            } else {
                if (startSec <= startHour && endSec > endHour) {
                    answer++;
                }
            }

            if(startSec > endSec && startMin < endMin) {
                if (startSec <= startMin)
                    answer++;
                if(endSec > endMin)
                    answer++;
            } else {
                if (startSec <= startMin && endSec > endMin) {
                    answer++;
                }
            }
        }

        if (hourHandStatPos == secHandStatPos || minHandStatPos == secHandStatPos) {
            answer++;
        }
        return answer;
    }

}
