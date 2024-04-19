import java.util.HashMap;

class Solution {
    HashMap<Character, Integer> alphabetMap = new HashMap<>();
    String name;
    int minMove = Integer.MAX_VALUE, length;
    boolean[] finish;


    public int solution(String name) {
        this.name = name;
        length = name.length();
        finish = new boolean[length];

        int idx = 0;
        for (int i = 65; i <= 90; i++)
            alphabetMap.put((char) i, idx++);

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                finish[i] = true;
            }
        }

        finish[0] = true;
        changeAlphabet(0, 0);

        return minMove == Integer.MAX_VALUE ? 0 : minMove;
    }

    void changeAlphabet(int curIdx, int curMove) {
        if (curMove >= minMove)
            return;

        curMove += Math.min(alphabetMap.get(name.charAt(curIdx)), 26 - alphabetMap.get(name.charAt(curIdx))); //변환하는데 필요한 이동

        for (int nextIdx = 0; nextIdx < length; nextIdx++) { // 다음으로 이동할 문자 정하기
            if (finish[nextIdx] || curIdx == nextIdx) //이미 끝났거나 현재 문자이면 패스~
                continue;

            int dis = (curIdx < nextIdx) ? //다음 문자까지 이동하는 거리
                    Math.min(nextIdx - curIdx, curIdx + length - nextIdx) : //현재가 다음보다 작을 경우
                    Math.min(curIdx - nextIdx, nextIdx + length - curIdx); //다음이 현재보다 작을 경우

            finish[nextIdx] = true;
            changeAlphabet(nextIdx, curMove + dis); //다음으로
            finish[nextIdx] = false;
        }

        if (finish()) // 다 끝났으면
            minMove = Math.min(minMove, curMove);
    }

    boolean finish() {
        for (boolean b : finish)
            if (!b) return false;
        return true;
    }
}