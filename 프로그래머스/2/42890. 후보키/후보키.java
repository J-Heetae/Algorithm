import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Solution {
    int rowSize, columnSize;
    String[][] relation;
    ArrayList<String> candidateKeys;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[][] {
            {"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});
    }

    public int solution(String[][] relation) {
        this.rowSize = relation.length;
        this.columnSize = relation[0].length;
        this.relation = relation;
        this.candidateKeys = new ArrayList<>();

        for (int size = 1; size <= columnSize; size++) {
            pick(size, 0, new StringBuilder());
        }

        return candidateKeys.size();
    }

    void pick(int size, int idx, StringBuilder curPick) {
        if (curPick.length() == size) {
            String candidate = curPick.toString();
            if (isUnique(candidate) && isCandidateKey(candidate)) {
                candidateKeys.add(candidate);
            }
            return;
        }
        for (int i = idx; i < columnSize; i++) {
            curPick.append(i);
            pick(size, i + 1, curPick);
            curPick.deleteCharAt(curPick.length() - 1);
        }
    }

    boolean isUnique(String picked) {
        for (String key : candidateKeys) {
            int matchCount = 0;
            for (char c : key.toCharArray()) {
                if(picked.indexOf(c) != -1) {
                    matchCount++;
                }
            }
            if(matchCount == key.length()) {
                return false;
            }
        }
        return true;
    }

    boolean isCandidateKey(String picked) {
        Set<String> checkSet = new HashSet<>();
        for (int i = 0; i < rowSize; i++) {
            StringBuilder cur = new StringBuilder();
            for (char c : picked.toCharArray()) {
                cur.append(relation[i][c - '0']);
            }
            if (checkSet.contains(cur.toString())) {
                return false;
            } else {
                checkSet.add(cur.toString());
            }
        }
        return true;
    }
}