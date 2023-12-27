import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s) {
            return new int[]{-1};
        }

        int[] tmp = new int[n];
        int value = s / n;
        int remain = s % n;

        Arrays.fill(tmp, value);

        int idx = tmp.length - 1;
        for(int i=0; i<remain; i++) {
            tmp[idx] += 1;
            idx--;
        }

        return tmp;
    }
}