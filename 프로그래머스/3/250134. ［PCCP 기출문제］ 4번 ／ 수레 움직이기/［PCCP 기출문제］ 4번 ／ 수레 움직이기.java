import java.util.LinkedList;
import java.util.Queue;

class Solution {
    final int[] DX = {-1, 0, 1, 0}, DY = {0, 1, 0, -1};

    int row, col;
    int minMove = Integer.MAX_VALUE;
    int[] redStart, redEnd, blueStart, blueEnd;
    int[][] maze;


    public int solution(int[][] maze) {
        row = maze.length;
        col = maze[0].length;
        this.maze = maze;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 1) redStart = new int[]{i, j};
                if (maze[i][j] == 3) redEnd = new int[]{i, j};
                if (maze[i][j] == 2) blueStart = new int[]{i, j};
                if (maze[i][j] == 4) blueEnd = new int[]{i, j};
            }
        }

        boolean[][] redVisited = new boolean[row][col];
        boolean[][] blueVisited = new boolean[row][col];

        redVisited[redStart[0]][redStart[1]] = true;
        blueVisited[blueStart[0]][blueStart[1]] = true;

        Queue<MazeContext> queue = new LinkedList<>();
        queue.offer(new MazeContext(0, redStart, blueStart, redVisited, blueVisited));

        while (!queue.isEmpty()) {
            MazeContext cur = queue.poll();

            int[] red = cur.redPos;
            int[] blue = cur.bluePos;

            if (red[0] == redEnd[0] && red[1] == redEnd[1] && blue[0] == blueEnd[0] && blue[1] == blueEnd[1]) {
                minMove = cur.moveCount;
                break;
            }

            if (red[0] != redEnd[0] || red[1] != redEnd[1]) {
                int nextRx, nextRy;

                for (int i = 0; i < 4; i++) {
                    nextRx = red[0] + DX[i];
                    nextRy = red[1] + DY[i];

                    if (nextRx < 0 || nextRx >= row || nextRy < 0 || nextRy >= col) //미로 벗어나는 위치면 패스
                        continue;

                    if (cur.redVisited[nextRx][nextRy]) //이미 방문했으면 패스
                        continue;

                    if (maze[nextRx][nextRy] == 5) //벽이면 패스
                        continue;

                    if (blue[0] == blueEnd[0] && blue[1] == blueEnd[1] && nextRx == blue[0] && nextRy == blue[1]) //도착한 파란 수레랑 겹치면 패스
                        continue;

                    int nextBx = blue[0], nextBy = blue[1];
                    if (blue[0] != blueEnd[0] || blue[1] != blueEnd[1]) {
                        for (int j = 0; j < 4; j++) {
                            nextBx = blue[0] + DX[j];
                            nextBy = blue[1] + DY[j];

                            if (nextBx < 0 || nextBx >= row || nextBy < 0 || nextBy >= col) //미로 벗어나는 위치면 패스
                                continue;

                            if (cur.blueVisited[nextBx][nextBy]) //이미 방문했으면 패스
                                continue;

                            if (maze[nextBx][nextBy] == 5) //벽이면 패스
                                continue;

                            if (nextBx == nextRx && nextBy == nextRy) //다음 빨간수레랑 겹치면
                                continue;

                            if(red[0] == nextBx && red[1] == nextBy && blue[0] == nextRx && blue[1] == nextRy)
                                continue;

                            boolean[][] copyRedVisited = new boolean[row][col];
                            boolean[][] copyBlueVisited = new boolean[row][col];

                            for (int k = 0; k < row; k++) {
                                for (int z = 0; z < col; z++) {
                                    copyRedVisited[k][z] = cur.redVisited[k][z];
                                    copyBlueVisited[k][z] = cur.blueVisited[k][z];
                                }
                            }

                            copyRedVisited[nextRx][nextRy] = true;
                            copyBlueVisited[nextBx][nextBy] = true;

                            queue.offer(new MazeContext(cur.moveCount + 1, new int[]{nextRx, nextRy}, new int[]{nextBx, nextBy}, copyRedVisited, copyBlueVisited));
                        }
                    } else {
                        boolean[][] copyRedVisited = new boolean[row][col];
                        boolean[][] copyBlueVisited = new boolean[row][col];

                        for (int k = 0; k < row; k++) {
                            for (int z = 0; z < col; z++) {
                                copyRedVisited[k][z] = cur.redVisited[k][z];
                                copyBlueVisited[k][z] = cur.blueVisited[k][z];
                            }
                        }

                        copyRedVisited[nextRx][nextRy] = true;
                        copyBlueVisited[nextBx][nextBy] = true;

                        queue.offer(new MazeContext(cur.moveCount + 1, new int[]{nextRx, nextRy}, new int[]{nextBx, nextBy}, copyRedVisited, copyBlueVisited));
                    }
                }
            }

            if (blue[0] != blueEnd[0] || blue[0] != blueEnd[1]) {
                int nextBx, nextBy;

                for (int i = 0; i < 4; i++) {
                    nextBx = blue[0] + DX[i];
                    nextBy = blue[1] + DY[i];

                    if (nextBx < 0 || nextBx >= row || nextBy < 0 || nextBy >= col) //미로 벗어나는 위치면 패스
                        continue;

                    if (cur.blueVisited[nextBx][nextBy]) //이미 방문했으면 패스
                        continue;

                    if (maze[nextBx][nextBy] == 5) //벽이면 패스
                        continue;

                    if (red[0] == redEnd[0] && red[1] == redEnd[1] && nextBx == red[0] && nextBy == red[1]) //도착한 빨간 수레랑 겹치면 패스
                        continue;

                    int nextRx = red[0], nextRy = red[1];
                    if (red[0] != redEnd[0] || red[1] != redEnd[1]) {
                        for (int j = 0; j < 4; j++) {
                            nextRx = red[0] + DX[i];
                            nextRy = red[1] + DY[i];

                            if (nextRx < 0 || nextRx >= row || nextRy < 0 || nextRy >= col) //미로 벗어나는 위치면 패스
                                continue;

                            if (cur.redVisited[nextRx][nextRy]) //이미 방문했으면 패스
                                continue;

                            if (maze[nextRx][nextRy] == 5) //벽이면 패스
                                continue;

                            if (nextBx == nextRx && nextBy == nextRy) //다음 빨간수레랑 겹치면
                                continue;

                            if(red[0] == nextBx && red[1] == nextBy && blue[0] == nextRx && blue[1] == nextBy)
                                continue;

                            boolean[][] copyRedVisited = new boolean[row][col];
                            boolean[][] copyBlueVisited = new boolean[row][col];

                            for (int k = 0; k < row; k++) {
                                for (int z = 0; z < col; z++) {
                                    copyRedVisited[k][z] = cur.redVisited[k][z];
                                    copyBlueVisited[k][z] = cur.blueVisited[k][z];
                                }
                            }

                            copyRedVisited[nextRx][nextRy] = true;
                            copyBlueVisited[nextBx][nextBy] = true;

                            queue.offer(new MazeContext(cur.moveCount + 1, new int[]{nextRx, nextRy}, new int[]{nextBx, nextBy}, copyRedVisited, copyBlueVisited));
                        }
                    } else {
                        boolean[][] copyRedVisited = new boolean[row][col];
                        boolean[][] copyBlueVisited = new boolean[row][col];

                        for (int k = 0; k < row; k++) {
                            for (int z = 0; z < col; z++) {
                                copyRedVisited[k][z] = cur.redVisited[k][z];
                                copyBlueVisited[k][z] = cur.blueVisited[k][z];
                            }
                        }

                        copyRedVisited[nextRx][nextRy] = true;
                        copyBlueVisited[nextBx][nextBy] = true;

                        queue.offer(new MazeContext(cur.moveCount + 1, new int[]{nextRx, nextRy}, new int[]{nextBx, nextBy}, copyRedVisited, copyBlueVisited));
                    }
                }
            }
        }

        return (minMove == Integer.MAX_VALUE) ? 0 : minMove;
    }

    class MazeContext {
        int moveCount;
        int[] redPos, bluePos;
        boolean[][] redVisited, blueVisited;

        public MazeContext(int moveCount, int[] redPos, int[] bluePos, boolean[][] redVisited, boolean[][] blueVisited) {
            this.moveCount = moveCount;
            this.redPos = redPos;
            this.bluePos = bluePos;
            this.redVisited = redVisited;
            this.blueVisited = blueVisited;
        }
    }
}