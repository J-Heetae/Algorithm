class Solution {
    int lastTime, advTime;
    int[] timeArr;

    public String solution(String play_time, String adv_time, String[] logs) {
        lastTime = stringTimeToInt(play_time);
        advTime = stringTimeToInt(adv_time);
        timeArr = new int[lastTime + 2];

        addLog(logs);
        int maxStart = getMaxTimeStart();
        return intTimeToString(maxStart);
    }

    private void addLog(String[] logs) {
        for (String log : logs) {
            String[] split = log.split("-");
            timeArr[stringTimeToInt(split[0])] += 1;
            timeArr[stringTimeToInt(split[1])] -= 1;
        }

        for (int i = 1; i <= lastTime; i++)
            timeArr[i] += timeArr[i - 1];
    }

    private int getMaxTimeStart() {
        long totalTime = 0;
        for (int i=0; i<advTime; i++)
            totalTime += timeArr[i];

        long maxTime = totalTime;
        int maxTimeStart = 0;
        for (int i = advTime; i < lastTime; i++) {
            totalTime += timeArr[i] - timeArr[i - advTime];
            if(totalTime > maxTime) {
                maxTime = totalTime;
                maxTimeStart = i - advTime + 1;
            }
        }
        return maxTimeStart;
    }

    private int stringTimeToInt(String str) {
        String[] split = str.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    private String intTimeToString(int time) {
        String hour = String.format("%02d", time / 3600);
        time %= 3600;
        String min = String.format("%02d", time / 60);
        time %= 60;
        String second = String.format("%02d", time);

        return hour + ":" + min + ":" + second;
    }
}