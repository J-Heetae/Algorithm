import java.util.*;

class Solution {
    
    static int index = 0;
    final String IN = "IN";

    public int[] solution(int[] fees, String[] records) {
        HashMap<String, Integer> indexMap = new HashMap<>();
        int[] inTime = new int[1000];
        int[] totalTime = new int[1000];
        Arrays.fill(inTime, -1);
    
        for(String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            int time = timeStringToInt(st.nextToken());
            String carNumber = st.nextToken();
            String status = st.nextToken();
            
            if(!indexMap.containsKey(carNumber)) {
                indexMap.put(carNumber, index++);
            }
            
            int carIndex = indexMap.get(carNumber);
            if(status.equals(IN)) {
                inTime[carIndex] = time;
            } else {
                totalTime[carIndex] += time - inTime[carIndex];
                inTime[carIndex] = -1;
            }
        }
        
        for(int i=0; i<1000; i++) {
            if(inTime[i] != -1) {
                totalTime[i] += (23 * 60 + 59) - inTime[i];
            }
        }
        
        ArrayList<int[]> carNumberList = new ArrayList<>();
        for(String str : indexMap.keySet()) {
            int carNumber = Integer.parseInt(str);
            int carIndex = indexMap.get(str);
            carNumberList.add(new int[]{carNumber, carIndex});
        }
        
        carNumberList.sort(Comparator.comparingInt(a -> a[0]));
        
        int[] answer = new int[carNumberList.size()];
        int answerIndex = 0;
        for(int[] carNumberIndex : carNumberList) {
            int carIndex = carNumberIndex[1];
            int curTotalTime = totalTime[carIndex];
            
            int fee;
            if(curTotalTime <= fees[0]) {
                fee = fees[1];
            } else {
                int add = (((curTotalTime - fees[0]) % fees[2]) > 0) ? 1 : 0;
                fee = fees[1] + ((curTotalTime - fees[0]) / fees[2] + add) * fees[3];
            }
            answer[answerIndex++] = fee;
        }
        return answer;
    }
    
    int timeStringToInt(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        return hour * 60 + min;
    }
}