class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;
        
        for(int[] item : info) {
            int costA = item[0];
            int costB = item[1];
            boolean[][] next = new boolean[n][m];
            
            for(int a=0; a<n; a++) {
                for(int b=0; b<m; b++) {
                    if(!dp[a][b]) {
                        continue;
                    }
                    
                    if(a + costA < n) {
                        next[a + costA][b] = true;
                    }
                    
                    if(b + costB < m) {
                        next[a][b + costB] = true;
                    }
                }
            }
            dp = next;
        }
        
        for(int a=0; a<n; a++) {
            for(int b=0; b<m; b++) {
                if(dp[a][b]) return a;
            }
        }
        return -1;
    }
}