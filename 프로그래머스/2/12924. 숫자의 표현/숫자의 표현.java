class Solution {
    public int solution(int n) {
        int answer = 0;
        int left = 1, right = 1, currSum = 1;

        while (right <= n) {
            if (currSum == n) {
                answer++;
            }

            if (currSum >= n) {
                currSum -= left;
                left++;
            } else {
                right++;
                currSum += right;
            }
        }
        return answer;
    }
}
