class Solution {
    public int solution(int[] players, int m, int k) {
        int[] expired = new int[24];
        int currServer = 0;
        int scaleOut = 0;
        
        for(int hour = 0; hour < 24; hour++) {
            currServer -= expired[hour];
            
            int required = players[hour] / m;
            
            if(required > currServer) {
                int toAdd = required - currServer;
                currServer += toAdd;
                scaleOut += toAdd;
                
                int expiredTime = hour + k;
                if(expiredTime <= 23) {
                    expired[expiredTime] += toAdd;   
                }
            }
        }
        return scaleOut;
    }
}