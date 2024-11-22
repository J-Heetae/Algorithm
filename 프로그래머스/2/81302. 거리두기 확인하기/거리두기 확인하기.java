import java.util.*;

class Solution {
    
    static String[][] rooms;

    final int NUM_OF_ROOM = 5;
    final int ROW = 5;
    final int COL = 5;
    
    final int[] DX = new int[]{0, 1, 0, -1};
    final int[] DY = new int[]{1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        rooms = places;
        int[] answer = new int[NUM_OF_ROOM];
        for(int roomIdx=0; roomIdx<NUM_OF_ROOM; roomIdx++) {
            answer[roomIdx] = roomCheck(roomIdx);
        }
        return answer;
    }
    
    int roomCheck(int roomIdx) {
        for(int r=0; r<ROW; r++) {
            for(int c=0; c<COL; c++) {
                if(rooms[roomIdx][r].charAt(c) == 'P') {
                    if(!isSafe(roomIdx, r, c)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    boolean isSafe(int roomIdx, int r, int c) {
        boolean[][] visited = new boolean[ROW][COL];
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{r,c,0}); // {가로, 세로, 거리}
        visited[r][c] = true;
        while(!que.isEmpty()) {
            int[] poll = que.poll();
            int curRow = poll[0];
            int curCol = poll[1];
            int curDis = poll[2];
            
            int manhattanDis = Math.abs(curRow - r) + Math.abs(curCol - c);
            if(manhattanDis >= 2) {
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nr = curRow + DX[i];
                int nc = curCol + DY[i];

                if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                if(rooms[roomIdx][nr].charAt(nc) == 'X') {
                    continue;
                }
                
                
                if(rooms[roomIdx][nr].charAt(nc) == 'P' && curDis <= 1) {
                    return false;
                }
                
                que.offer(new int[]{nr, nc, curDis + 1});
                visited[nr][nc] = true;
            }
        }
        return true;
    }
}