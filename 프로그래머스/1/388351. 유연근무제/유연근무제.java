import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int answer = 0;
        
        for(int idx=0; idx<n; idx++) {
            int schedule = schedules[idx];
            int[] timelog = timelogs[idx];
            
            int limitTime = getLimitTime(schedule);

            boolean canReward = true;
            int currStartday = startday;
            for(int time : timelog) {
                if(currStartday != 6 && currStartday != 7) {
                    if(time > limitTime) {
                        canReward = false;
                        break;
                    }
                }
                currStartday++;
                if(currStartday > 7) {
                    currStartday = 1;
                }
            }
            
            if(canReward) {
                answer++;
            }
        }
        return answer;
    }
    
    int getLimitTime(int startTime) {
        int hour = startTime / 100;
        int min = startTime % 100;
        
        min += 10;
        
        if(min >= 60) {
            hour++;
            min -= 60;
        }
        return hour * 100 + min;
    }
}