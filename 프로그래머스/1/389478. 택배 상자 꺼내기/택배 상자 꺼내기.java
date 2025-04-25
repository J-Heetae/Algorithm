class Solution {
    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    
    public int solution(int n, int w, int num) {        
        int height = n / w;
        if(n % w > 0) {
            height++;
        }
        
        int[][] storage = new int[height][w];
        int[] idxArr = new int[n + 1];
        
        int boxNum = 1;
        int dir = 0;
        int x = 0, y = -1;
        while(boxNum <= n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(ny >= 0 && ny < w) {
                idxArr[boxNum] = ny;
                storage[nx][ny] = boxNum++;
                x = nx; y = ny;
            } else {
                dir = (dir + 1) % 4;
                continue;
            }
            
            if(dir % 2 == 1) {
                dir = (dir + 1) % 4;
            }
        }
        
        int idx = idxArr[num];
        int answer = 1;
        for(int i=height - 1; i>=0; i--) {
            if(storage[i][idx] == num) {
                break;
            }
            if(storage[i][idx] != 0) {
                answer++;
            }
        }
        return answer;
    }
}