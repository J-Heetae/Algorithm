class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[][]{{1, 0, 2}, {0, 0, 0}, {5, 0 ,5}, {4, 0, 3}});
    }
    final int RED = 0, BLUE = 1;
    final int[] DX = {-1, 0, 1, 0}, DY = {0, 1, 0, -1};
    int minMove = Integer.MAX_VALUE;
    int row, col;
    int[][] maze;
    boolean[][][] visited;

    public int solution(int[][] maze) {
        row = maze.length;
        col = maze[0].length;
        this.maze = maze;
        int[][] start = new int[2][2];
        visited = new boolean[2][row][col];

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(maze[i][j] == 1) start[RED] = new int[]{i, j};
                if(maze[i][j] == 2) start[BLUE] = new int[]{i, j};
            }
        }

        visited[RED][start[RED][0]][start[RED][1]] = true;
        visited[BLUE][start[BLUE][0]][start[BLUE][1]] = true;
        getMinMove(start, 0);

        return (minMove == Integer.MAX_VALUE) ? 0 : minMove;
    }

    void getMinMove(int[][] wagon, int move) {
        if (move > minMove)
            return;

        int rx = wagon[RED][0];
        int ry = wagon[RED][1];
        int bx = wagon[BLUE][0];
        int by = wagon[BLUE][1];

        for(int i=0; i<4; i++) {
            int nrx = (maze[rx][ry] == 3) ? rx : rx + DX[i];
            int nry = (maze[rx][ry] == 3) ? ry : ry + DY[i];

            if(nrx < 0 || nrx >= row || nry < 0 || nry >= col || maze[nrx][nry] == 5 || (maze[nrx][nry] != 3 && visited[RED][nrx][nry]))
                continue;

            for(int j=0; j<4; j++) {
                int nbx = (maze[bx][by] == 4) ? bx : bx + DX[j];
                int nby = (maze[bx][by] == 4) ? by : by + DY[j];

                if(nbx < 0 || nbx >= row || nby < 0 || nby >= col || maze[nbx][nby] == 5 || (maze[nbx][nby] != 4 && visited[BLUE][nbx][nby]))
                    continue;

                if(nrx == nbx && nry == nby) //다음으로 이동할 곳이 겹치면
                    continue;

                if(rx == nbx && ry == nby && bx == nrx && by == nry) //서로 교차해서 지나가면
                    continue;

                if(maze[nrx][nry] == 3 && maze[nbx][nby] == 4) { //둘 다 도착
                    minMove = Math.min(minMove, move + 1);
                    return;
                }

                visited[RED][nrx][nry] = true;
                visited[BLUE][nbx][nby] = true;
                getMinMove(new int[][]{{nrx, nry}, {nbx, nby}}, move + 1);
                visited[RED][nrx][nry] = false;
                visited[BLUE][nbx][nby] = false;
            }
        }
    }
}