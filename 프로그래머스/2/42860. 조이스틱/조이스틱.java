class Solution {
    String name;
    int minMove = Integer.MAX_VALUE, length;
    boolean[] finish;


    public int solution(String name) {
        this.name = name;
        length = name.length();
        finish = new boolean[length];

        int sum = 0;
        for (int i = 0; i < name.length(); i++) {
            char curChar = name.charAt(i);
            if (curChar == 'A') finish[i] = true; // 'A'면 조작 필요 X
            else sum += Math.min(curChar - 'A', 'Z' - curChar + 1); // 해당 문자로 바꾸는 최소 조작 횟수
        }

        finish[0] = true;
        changeAlphabet(0, sum);

        return minMove == Integer.MAX_VALUE ? 0 : minMove;
    }

    void changeAlphabet(int curIdx, int curMove) {
        if (curMove >= minMove) // 현재 조작수가 최소 조작수보다 크면
            return;

        if (finish()) {// 다 끝났으면
            minMove = Math.min(minMove, curMove); //비교
            return;
        }

        for (int nextIdx = 0; nextIdx < length; nextIdx++) { // 다음으로 이동할 문자 정하기
            if (finish[nextIdx] || curIdx == nextIdx) //이미 끝났거나 현재 문자이면 패스~
                continue;

            finish[nextIdx] = true;
            changeAlphabet(nextIdx, curMove + Math.min(Math.abs(nextIdx - curIdx), length - Math.abs(nextIdx - curIdx))); // 다음 문자까지 최소 거리
            finish[nextIdx] = false;
        }
    }

    boolean finish() {
        for (boolean b : finish)
            if (!b) return false;
        return true;
    }
}