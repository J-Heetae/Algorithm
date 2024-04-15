import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}});
    }

    int[][] dice;
    Stack<String> checked = new Stack<>();
    boolean[] checkArr;

    int win, maxWin, numberOfDice;
    int[] aDice, bDice, maxCombination;

    public int[] solution(int[][] dice) {
        this.dice = dice;
        numberOfDice = dice.length;
        aDice = new int[numberOfDice / 2];
        bDice = new int[numberOfDice / 2];
        maxCombination = new int[numberOfDice / 2];
        checkArr = new boolean[numberOfDice];
        chooseDice(0, 0);
        return maxCombination;
    }

    void chooseDice(int cur, int start) {
        if (cur == numberOfDice / 2) {
            StringBuilder sb = new StringBuilder();

            for (boolean b : checkArr) {
                if (b)
                    sb.append(1);
                else
                    sb.append(0);
            }

            if (!checked.contains(sb.toString())) {
                checked.add(sb.toString());

                int aIdx = 0, bIdx = 0;
                for (int i = 0; i < numberOfDice; i++) {
                    if (checkArr[i])
                        aDice[aIdx++] = i;
                    else
                        bDice[bIdx++] = i;
                }

                getWinCount(0, 0, 0, 0);
                if (win > maxWin) {
                    maxWin = win;
                    int idx = 0;
                    for (int i = 0; i < numberOfDice; i++) {
                        if (checkArr[i]) {
                            maxCombination[idx++] = i + 1;
                        }
                    }
                }
                win = 0;
            }
            return;
        }

        for (int i = start; i < numberOfDice; i++) {
            checkArr[i] = true;
            chooseDice(cur + 1, i + 1);
            checkArr[i] = false;
            chooseDice(cur, i + 1);
        }
    }

    void getWinCount(int aIdx, int bIdx, int aScore, int bScore) {
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();

        fillList(aList, 0, 0, true);
        fillList(bList, 0, 0, false);

        Collections.sort(bList);

        win = 0;
        for (int cur : aList) {
            int maxIdx = -1;
            int l = 0;
            int r = bList.size() - 1;
            while(l <= r) {
                int mid = (l + r) / 2;

                if (cur > bList.get(mid)) {
                    l = mid + 1;
                    maxIdx = Math.max(maxIdx, mid);
                } else if (cur <= bList.get(mid)) {
                    r = mid - 1;
                }
            }
            win += maxIdx + 1;
        }
    }

    void fillList(ArrayList<Integer> list, int idx, int cur, boolean team) {
        if (idx == numberOfDice / 2) {
            list.add(cur);
            return;
        }

        for (int i = 0; i < 6; i++) {
            if(team) fillList(list, idx + 1, cur + dice[aDice[idx]][i], true);
            else fillList(list, idx + 1, cur + dice[bDice[idx]][i], false);
        }
    }
}