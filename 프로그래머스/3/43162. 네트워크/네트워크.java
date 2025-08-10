import java.util.*;

class Solution {
    
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        
        int network = 0;
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                
                network++;
                
                Queue<Integer> que = new LinkedList<>();
                que.offer(i);
                while(!que.isEmpty()) {
                    int curr = que.poll();
                    for(int j=0; j<n; j++) {
                        if(j != curr && computers[curr][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            que.offer(j);
                        }
                    }
                }
                
            }
        }
        return network;
    }
}