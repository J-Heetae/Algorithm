class Solution {
    final int[][] fatigueArr = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int minFatigue = Integer.MAX_VALUE;
    int[] picks;
    int[] minerals;

    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = new int[minerals.length];
        for (int i = 0; i < minerals.length; i++) {
            switch (minerals[i]) {
                case "diamond":
                    this.minerals[i] = 0;
                    break;
                case "iron":
                    this.minerals[i] = 1;
                    break;
                case "stone":
                    this.minerals[i] = 2;
            }
        }
        mine(0, 0);
        return minFatigue;
    }

    boolean pickExhaustion() {
        for(int i = 0; i < picks.length; i++) {
            if(picks[i] != 0)
                return false;
        }
        return true;
    }

    void mine(int fatigue, int mineralIdx) {
        if (fatigue >= minFatigue)
            return;

        if (mineralIdx == minerals.length || pickExhaustion()) {
            minFatigue = Math.min(minFatigue, fatigue);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                int tempFatiue = fatigue;
                int nextMineralIdx = Math.min(minerals.length, mineralIdx + 5);
                for (int j = mineralIdx; j < nextMineralIdx; j++) {
                    tempFatiue += fatigueArr[i][minerals[j]];
                }
                mine(tempFatiue, nextMineralIdx);
                picks[i]++;
            }
        }

    }
}