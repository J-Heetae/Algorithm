import java.util.ArrayList;

class Solution {
    int[][] waitingTimeArr;
    ArrayList<int[]> combination;
    ArrayList<int[]>[] participants;

    public int solution(int k, int n, int[][] reqs) {
        int maxAgentEach = n - (k - 1);
        waitingTimeArr = new int[k + 1][maxAgentEach + 1];
        combination = new ArrayList();
        participants = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            participants[i] = new ArrayList<>();
        }

        for (int[] req : reqs) {
            participants[req[2]].add(new int[] {req[0], req[1]});
        }

        for (int type = 1; type <= k; type++) {
            if (participants[type].size() == 0) {
                continue;
            }
            for (int agent = 1; agent <= maxAgentEach; agent++) {
                getWaitingTiem(type, agent);
            }
        }

        getCombination(n - k, k, 1, new int[k + 1]);
        for (int[] ints : combination) {
            for(int i=0; i<ints.length; i++) {
                ints[i]++;
            }
        }

        int idx = 0;
        int minWaitingTime = Integer.MAX_VALUE;
        for (int[] agentPerType : combination) {
            int totalWaitingTime = 0;
            for(int type = 1; type <=k; type++) {
                int agent = agentPerType[type];
                totalWaitingTime += waitingTimeArr[type][agent];
            }
            minWaitingTime = Math.min(minWaitingTime, totalWaitingTime);
        }
        return minWaitingTime;
    }

    private void getCombination(int agent, int type, int idx, int[] current) {
        if (idx == type) {
            current[idx] = agent;
            combination.add(current.clone());
            return;
        }

        for (int i = 0; i <= agent; i++) {
            current[idx] = i;
            getCombination(agent - i, type, idx + 1, current);
        }
    }

    private void getWaitingTiem(int type, int agent) {
        int waitingTime = 0;
        ArrayList<int[]> reqList = participants[type];

        int idx = 0;
        int agentNum = agent;
        int[] curTime = new int[agentNum];
        while (idx < reqList.size()) {
            int[] req = reqList.get(idx); //현재 참가자
            int curAgent = 0;
            int minTime = Integer.MAX_VALUE;
            //제일 빨리 끝나는 상담원 정하기
            for (int i = 0; i < agentNum; i++) {
                if (curTime[i] < minTime) {
                    curAgent = i;
                    minTime = curTime[i];
                }
            }
            if (curTime[curAgent] < req[0]) {
                curTime[curAgent] = req[0];
            }

            waitingTime += (curTime[curAgent] - req[0] > 0) ? curTime[curAgent] - req[0] : 0; //기다린 시간 추가
            curTime[curAgent] += req[1]; //상담 종료 시각으로 변경
            idx++;
        }
        waitingTimeArr[type][agent] = waitingTime;
    }
}