import java.util.*;

class Solution {
    private static final int END = 23 * 60 + 59;
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimes = new HashMap<>();     // 차량별 마지막 IN 시각
        Map<String, Integer> acc = new TreeMap<>();         // 차량별 누적 주차 분(정렬 필요해 TreeMap)
        
        for (String rec : records) {
            String[] sp = rec.split(" ");
            int time = parseTime(sp[0]);
            String car = sp[1];
            String stat = sp[2];

            if (stat.equals("IN")) {
                inTimes.put(car, time);
            } else { // OUT
                int in = inTimes.remove(car);               // 반드시 존재
                acc.merge(car, time - in, Integer::sum);    // 누적 분 합산
            }
        }
        
         // 아직 OUT 안 한 차량은 23:59까지 누적
        for (Map.Entry<String, Integer> e : inTimes.entrySet()) {
            String car = e.getKey();
            int in = e.getValue();
            acc.merge(car, END - in, Integer::sum);
        }
        
        // 차량번호 오름차순으로 요금 계산
        int[] ans = new int[acc.size()];
        int i = 0;
        for (int minutes : acc.values()) {
            ans[i++] = calcFee(fees, minutes);
        }
        return ans;
    }
    
    private int calcFee(int[] f, int minutes) {
        int baseTime = f[0], baseFee = f[1], unitTime = f[2], unitFee = f[3];
        if (minutes <= baseTime) return baseFee;
        int over = minutes - baseTime;
        int units = (over + unitTime - 1) / unitTime;
        return baseFee + units * unitFee;
    }
    
    private int parseTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 +Integer.parseInt(t[1]);
    }
}