class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] copy = new int[triangle.length][triangle.length+1];
        
        int max = Integer.MIN_VALUE;
        
        copy[0][0] = triangle[0][0];
        for(int i=0; i<triangle.length; i++) {
            if(i < triangle.length -1) {
                for(int j=0; j<triangle[i].length; j++) {
                    copy[i+1][j] = Math.max(copy[i+1][j], copy[i][j] + triangle[i+1][j]);
                    copy[i+1][j+1] = Math.max(copy[i+1][j+1], copy[i][j] + triangle[i+1][j+1]);
                }
            } else {
                for(int j=0; j<triangle[i].length; j++) {
                    max = Math.max(max, copy[i][j]);
                }
            }
        }
        return max;
    }
}