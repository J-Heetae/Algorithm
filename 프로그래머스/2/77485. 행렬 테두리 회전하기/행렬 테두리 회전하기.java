class Solution {
    
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        initMap(rows, columns);
        int[] answer = new int[queries.length];
        int idx = 0;
        for(int[] query : queries) {
            answer[idx++] = rotate(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
        }
        return answer;
    }
    
    void initMap(int r, int c) {
        map = new int[r][c];
        int num = 1;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                map[i][j] = num++;
            }
        }
    }
    
    int rotate(int x1, int y1, int x2, int y2) {
        int x = x1;
        int y = y1;
        int dir = 0;
        int min = map[x1][y1];
        int tmp1 = map[x1][y1];
        int tmp2 = 0;
        while(dir < 4) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx < x1 || ny < y1 || nx > x2 || ny > y2) {
                dir++;
                continue;
            }
            tmp2 = map[nx][ny];
            map[nx][ny] = tmp1;
            tmp1 = tmp2;
            min = Math.min(min, tmp1);
            x = nx;
            y = ny;
        }
        return min;
    }
}