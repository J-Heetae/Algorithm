import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s)
            return new int[] {-1};

        int value = s / n;
        int left = s % n;

        int[] answer = new int[n];
        Arrays.fill(answer, value);

        while(left >= 1) {
            for(int i=answer.length-1; i>=0; i--) {
                if(left <= 0)
                    break;
                answer[i]++;
                left--;
            }
        }
        return answer;
    }
}