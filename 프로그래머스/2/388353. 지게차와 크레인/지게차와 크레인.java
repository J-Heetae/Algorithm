import java.util.*;

class Solution {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};
    
    static int n, m, remain;
    static int[][] map;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        remain = n * m;
        map = new int[n + 2][m + 2];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j) - 0;
            }
        }

        for(String req : requests) {
            int removeNum = req.charAt(0) - 0;
            if(req.length() > 1) {
                removeAll(removeNum);
            } else {
                remove(removeNum);
            }
        }
        
        return remain;
    }
    
    void remove(int removeNum) {
        boolean[][] removeArr = new boolean[n + 2][m + 2];
        boolean[][] visited = new boolean[n + 2][m + 2];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            
            for(int i=0; i<4; i++) {
                int nx = poll[0] + DX[i];
                int ny = poll[1] + DY[i];
                
                if(nx < 0 || ny < 0 || nx > n + 1 || ny > m + 1 || visited[nx][ny]) {
                    continue;
                }
                
                if(map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                } else if (map[nx][ny] == removeNum) {
                    removeArr[nx][ny] = true;
                }
            }
        }
        
        for(int i=0; i<removeArr.length; i++) {
            for(int j=0; j<removeArr[0].length; j++) {
                if(removeArr[i][j]) {
                    remain--;
                    map[i][j] = 0;
                }
            }
        }
    }
    
    void removeAll(int removeNum) {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(map[i][j] == removeNum) {
                    map[i][j] = 0;
                    remain--;
                }
            }
        }
    }
}