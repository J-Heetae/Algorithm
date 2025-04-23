import java.util.*;

class Solution {
    
    static int m;
    static int answer = 0;
    static boolean[] code;
    
    static int maxNum;
    static int[] response;
    static int[][] query;
    
    public int solution(int n, int[][] q, int[] ans) {
        code = new boolean[n + 1];
        m = q.length;
        
        maxNum = n;
        query = q;
        response = ans;
        
        pick(0, 0);
        
        return answer;
    }
    
    void pick(int idx, int beforeNum) {
        if(idx == 5) {
            if(check()) {
                answer++;
            }
            return;
        }
        
        for(int i = beforeNum + 1; i <= maxNum; i++) {
            code[i] = true;
            pick(idx + 1, i);
            code[i] = false;
        }
    }
    
    boolean check() {
        for(int i=0; i<m; i++) {
            int count = 0;
            
            for(int num : query[i]) {
                if(code[num]) {
                    count++;
                }
            }
            
            if(response[i] != count) {
                return false;
            }
        }
        return true;
    }
}