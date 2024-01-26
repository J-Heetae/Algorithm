import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] copy = new int[triangle.length][];
        for(int i=0; i<triangle.length; i++) {
            copy[i] = new int[triangle[i].length];
        }
        
        copy[0][0] = triangle[0][0];
        for(int i=0; i<triangle.length - 1; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                copy[i+1][j] = Math.max(copy[i+1][j], copy[i][j] + triangle[i+1][j]);
                copy[i+1][j+1] = Math.max(copy[i+1][j+1], copy[i][j] + triangle[i+1][j+1]);
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<triangle[triangle.length - 1].length; i++) {
            max = Math.max(max, copy[triangle.length - 1][i]);
        }

        return max;
    }
}