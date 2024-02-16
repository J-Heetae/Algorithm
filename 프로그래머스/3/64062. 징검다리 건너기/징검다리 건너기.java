class Solution {
    public int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }

        int answer = Integer.MIN_VALUE;
        int mid;
        while (min <= max) {
            mid = (max + min) / 2;
            if (isPossible(stones, k, mid)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return answer;
    }

    private boolean isPossible(int[] stones, int k, int mid) {
        int cnt = 0;
        for (int stone : stones) {
            cnt = (stone - mid < 0) ? cnt + 1 : 0;
            if (cnt == k)
                return false;
        }
        return true;
    }
}