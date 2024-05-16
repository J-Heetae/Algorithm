import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int length = progresses.length;
        int[] day = new int[length];

        for (int i = 0; i < progresses.length; i++) {
            int curProgress = progresses[i];
            int curSpeed = speeds[i];

            int curLeft = 100 - curProgress;
            int remain = curLeft % curSpeed;
            day[i] = curLeft / curSpeed + ((remain != 0) ? 1 : 0);
        }

        ArrayList<Integer> list = new ArrayList<>();
        int before = day[0];
        int count = 1;
        for(int i=1; i<length; i++) {
            if(day[i] > before) {
                list.add(count);
                before = day[i];
                count = 1;
                continue;
            }
            count++;
        }
        list.add(count);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}