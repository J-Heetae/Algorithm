import java.util.ArrayList;

class Solution {
    int[][] weakArr;
    boolean[] distUsed;
    ArrayList<int[]> distList = new ArrayList<>();
    int minFriend = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        makeWeakArr(n, weak);
        distUsed = new boolean[dist.length];
        makeDistArr(0, dist, new int[dist.length]);
        inspection();
        return (minFriend == Integer.MAX_VALUE) ? -1 : minFriend;
    }

    void inspection() {
        for (int[] curWeak : weakArr) {
            outer:
            for (int[] curDist : distList) {
                int distIdx = 0;
                int cur = curWeak[0] + curDist[distIdx++];
                for (int i = 1; i < curWeak.length; i++) {
                    if (curWeak[i] > cur) {
                        if (distIdx >= curDist.length)
                            continue outer;
                        cur = curWeak[i] + curDist[distIdx++];
                    }
                }
                minFriend = Math.min(minFriend, distIdx);
            }
        }
    }

    void makeDistArr(int idx, int[] dist, int[] tmp) {
        if (idx == dist.length) {
            int[] add = new int[dist.length];
            System.arraycopy(tmp, 0, add, 0, dist.length);
            distList.add(add);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (distUsed[i])
                continue;

            distUsed[i] = true;
            tmp[idx] = dist[i];
            makeDistArr(idx + 1, dist, tmp);
            tmp[idx] = 0;
            distUsed[i] = false;
        }
    }

    void makeWeakArr(int n, int[] weak) {
        weakArr = new int[weak.length][weak.length];

        weakArr[0] = weak;
        for (int i = 1; i < weak.length; i++) {
            int idx = i;
            for (int j = 0; j < weak.length; j++) {
                weakArr[i][j] = weak[idx % weak.length] + n * (idx / weak.length);
                idx++;
            }
        }
    }
}