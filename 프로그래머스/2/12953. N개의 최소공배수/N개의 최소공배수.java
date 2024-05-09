import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int answer = arr[0];
        boolean pass = false;
        while(!pass) {
            pass = true;
            for (int i : arr) {
                if(answer < i || answer % i != 0) {
                    pass = false;
                    break;
                }
            }
            if(pass)
                break;
            pass = false;
            answer++;
        }
        return answer;
    }
}