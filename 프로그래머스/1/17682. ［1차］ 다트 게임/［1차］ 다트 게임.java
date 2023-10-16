class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("1S2D*3T"));
    }
    public int solution(String dartResult) {
        int[] scores = new int[3];
        int socoreIdx = -1;

        int length = dartResult.length();
        for(int idx=0; idx<length; idx++) {
            char cur = dartResult.charAt(idx);

            if(cur >= '0' && cur <= '9') {
                scores[++socoreIdx] = cur - '0';
                if(cur == '1' && dartResult.charAt(idx+1) == '0') {
                    idx++;
                    scores[socoreIdx] = 10;
                }
            } else if(cur == 'D') {
                scores[socoreIdx] = (int)Math.pow(scores[socoreIdx],2);
            } else if(cur == 'T') {
                scores[socoreIdx] = (int)Math.pow(scores[socoreIdx],3);
            } else if(cur == '*') {
                if(socoreIdx != 0) {
                    scores[socoreIdx-1] *= 2;
                }
                scores[socoreIdx] *= 2;
            } else if(cur == '#') {
                scores[socoreIdx] *= -1;
            }
        }
        int sum = 0;
        for(int score : scores) {
            sum += score;
        }
        return sum;
    }
}