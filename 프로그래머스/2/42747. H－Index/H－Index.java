import java.util.*;

class Solution {
    public int solution(int[] citations) {  
        Arrays.sort(citations);
        
        int length = citations.length;
        
        int hIndex = Integer.MIN_VALUE;
        int minCitation = 0;
        int maxCitation = citations[length - 1];
        while(minCitation <= maxCitation) {
            int currCitation = (minCitation + maxCitation) / 2;
            
            int minIndex = Integer.MAX_VALUE;
            int leftIndex = 0;
            int rightIndex = length - 1;
            while(leftIndex <= rightIndex) {
                int currIndex = (leftIndex + rightIndex) / 2;
                if(citations[currIndex] < currCitation) {
                    leftIndex = currIndex + 1;
                } else {
                    minIndex = Math.min(minIndex, currIndex);
                    rightIndex = currIndex - 1;
                }
            }
            
            int enoughCitationCount = length - minIndex;
            if(enoughCitationCount >= currCitation) {
                hIndex = Math.max(hIndex, currCitation);
                minCitation = currCitation + 1;
            } else {
                maxCitation = currCitation - 1;
            }
        }
        return hIndex;
    }
}