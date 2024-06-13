import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] reached = new int[N + 3];
        int[] faild = new int[N + 2];

        ArrayList<FailureRate> rateList = new ArrayList<>();
        reached[0] += stages.length;
        for (int stage : stages) {
            reached[stage + 1]--;
            faild[stage]++;
        }

        for(int i=1; i<reached.length; i++) {
            reached[i] += reached[i - 1];
        }

        for(int i=1; i<=N; i++) {
            rateList.add(new FailureRate(i, (double)faild[i] / reached[i]));
        }
        Collections.sort(rateList);

        int[] answer = new int[rateList.size()];
        for(int i=0; i<rateList.size(); i++) {
            answer[i] = rateList.get(i).stage;
        }
        return answer;
    }

    class FailureRate implements Comparable<FailureRate> {
        int stage;
        double rate;

        public FailureRate(int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }

        @Override
        public int compareTo(FailureRate o) {
            if (this.rate > o.rate) {
                return -1;
            } else if (this.rate < o.rate) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}