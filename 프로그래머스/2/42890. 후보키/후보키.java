import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[][] {
            {"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});
    }

    int answer = 0;
    int rowSize, columnSize;
    String[][] relation;
    ArrayList<String> set = new ArrayList<>();

    public int solution(String[][] relation) {
        rowSize = relation.length;
        columnSize = relation[0].length;
        this.relation = relation;

        for (int size = 1; size <= columnSize; size++) {
            //일단 골라야지 사이즈에 맞게
            pick(size, 0, "");
        }
        return set.size();
    }

    void pick(int size, int idx, String curPick) {
        if (size == curPick.length() && check(curPick)) {
            //검사 시작
            checkCandi(curPick);
            return;
        }
        //사이즈 맞춰서 고르기
        for (int i = idx; i < columnSize; i++) {
            pick(size, i + 1, curPick + i);
        }
    }

    boolean check(String picked) {
        outer:
        for (String s : set) {
            int length = s.length();
            char[] charArr = s.toCharArray();
            boolean[] flag = new boolean[charArr.length];

            for (char c : picked.toCharArray()) {
                for(int i=0; i<charArr.length; i++) {
                    if(c == charArr[i]) {
                        flag[i] = true;
                    }
                }
            }
            for (boolean b : flag) {
                if(!b) continue outer;
            }
            return false;
        }
        return true;
    }

    void checkCandi(String picked) {
        int[] columnArr = new int[picked.length()];
        for (int i = 0; i < picked.length(); i++) {
            columnArr[i] = picked.charAt(i) - '0';
        }

        Set<String> checkSet = new HashSet<>();
        for (int i = 0; i < rowSize; i++) {
            String cur = "";
            for (int j = 0; j < columnArr.length; j++) {
                cur += relation[i][columnArr[j]];
            }

            if (checkSet.contains(cur)) {
                return;
            } else {
                checkSet.add(cur);
            }
        }
        set.add(picked);
    }
}