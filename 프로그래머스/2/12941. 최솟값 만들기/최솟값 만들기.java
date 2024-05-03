import java.util.Arrays;
import java.util.Collections;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        int length = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        for(int i=0; i<length/2; i++) {
            int tmp = B[i];
            B[i] = B[length - 1 - i];
            B[length - 1 - i] = tmp;
        }

        for(int i=0; i<length; i++) {
            answer += A[i] * B[i];
        }
        return answer;
    }
}