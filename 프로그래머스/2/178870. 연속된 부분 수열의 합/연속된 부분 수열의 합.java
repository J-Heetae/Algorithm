class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[] {1, 1, 1, 2, 3, 4, 5}, 5);
    }
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};

        int minLeftIdx = Integer.MAX_VALUE;
        int minRightIdx = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        int sum = sequence[left];

        while (right < sequence.length) {
            if (sum < k) {
                right++;
                if (right == sequence.length) {
                    break;
                }
                sum += sequence[right];
                continue;
            }

            if (sum == k) {
                if (minLength > right - left) {
                    minLength = right - left;
                    minLeftIdx = left;
                    minRightIdx = right;
                }
                sum -= sequence[left];
                left++;
                continue;
            }

            if (sum > k) {
                sum -= sequence[left];
                left++;
            }
        }

        return new int[]{minLeftIdx, minRightIdx};
    }
}