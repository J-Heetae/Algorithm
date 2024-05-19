class Solution {
    int answer = 0;
    public int solution(int n) {
        make(0, n, n);
        return answer;
    }

    void make(int curLeft, int left, int right) {
        if(curLeft == 0 && left == 0 && right == 0) {
            answer++;
            return;
        }
        if(curLeft == 0) {
            make(1, left - 1, right);
        } else {
            if(left == 0) {
                make(curLeft - 1, 0, right - 1);
            } else {
                make(curLeft - 1, left, right - 1);
                make(curLeft + 1, left - 1, right);
            }
        }
    }
}