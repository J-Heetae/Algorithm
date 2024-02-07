import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        int answer = 0;
        int camera = Integer.MIN_VALUE;
        for (int[] route : routes) {
            if (route[0] > camera) {
                camera = route[1];
                answer++;
            }
        }
        return answer;
    }
}