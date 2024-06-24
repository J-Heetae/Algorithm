import java.util.HashMap;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int userNum = id_list.length;

        HashMap<String, Integer> nameIdxMap = new HashMap<String, Integer>();
        for (int i = 0; i < userNum; i++) {
            nameIdxMap.put(id_list[i], i);
        }

        int[] reportedCnt = new int[userNum];
        boolean[][] reportArr = new boolean[userNum][userNum];
        for (String s : report) {
            String[] split = s.split(" ");
            int reporterIdx = nameIdxMap.get(split[0]);
            int reportedIdx = nameIdxMap.get(split[1]);
            if (!reportArr[reporterIdx][reportedIdx]) {
                reportArr[reporterIdx][reportedIdx] = true;
                reportedCnt[reportedIdx]++;
            }
        }

        int[] informCnt = new int[userNum];
        for (int i = 0; i < reportedCnt.length; i++) {
            if (reportedCnt[i] >= k) {
                for (int j = 0; j < userNum; j++) {
                    if (reportArr[j][i]) {
                        informCnt[j]++;
                    }
                }
            }
        }
        return informCnt;
    }
}