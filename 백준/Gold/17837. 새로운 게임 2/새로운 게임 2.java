import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    //상우하좌
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static int size, numOfPiece, turn;
    static int[][] board;
    static int[][][] board2;
    static Piece[] pieces;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        numOfPiece = Integer.parseInt(st.nextToken());
        board = new int[size][size];

        for(int i=0; i<size; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        board2 = new int[size][size][3];
        pieces = new Piece[numOfPiece+1];
        int x, y, dir;
        for(int num=1; num<=numOfPiece; num++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());

            if(dir == 2) dir = 3;
            else if(dir == 3) dir = 0;
            else if(dir == 4) dir = 2;

            board2[x][y][0] = num;
            pieces[num] = new Piece(dir, x, y);
        }

        for(turn=1; turn<=1001; turn++) {
            if(turn == 1001) {
                turn = -1;
                break;
            }

            for(int num=1; num<=numOfPiece; num++) {
                Piece cur = pieces[num];

                int nx = cur.x + DX[cur.dir];
                int ny = cur.y + DY[cur.dir];

                if(nx < 0 || ny < 0 || nx >= size || ny >= size ||
                   board[nx][ny] == 2) {
                    blue(num, cur.x, cur.y);

                } else if(board[nx][ny] == 0) {
                    white(num, cur.x, cur.y, nx, ny);

                } else if(board[nx][ny] == 1) {
                    red(num, cur.x, cur.y, nx, ny);
                }
            }
        }
        System.out.println(turn);
    }

    private static void white(int num, int x, int y, int nx, int ny) {
        int[] before = board2[x][y];
        int[] next = board2[nx][ny];

        int beforeFromIdx = getIdx(num, before);
        int beforeToIdx = getEmptyIdx(before) - 1;
        if(beforeToIdx == -2) {
            beforeToIdx = 2;
        }

        int nextIdx = getEmptyIdx(next);
        for(int idx=beforeFromIdx; idx<=beforeToIdx; idx++) {
            if(nextIdx == 3 || nextIdx == -1) {
                System.out.println(turn);
                System.exit(0);
            }
            int curNum = board2[x][y][idx];
            pieces[curNum].x = nx;
            pieces[curNum].y = ny;

            board2[nx][ny][nextIdx] = board2[x][y][idx];
            board2[x][y][idx] = 0;
            nextIdx++;
        }
    }

    private static void red(int num, int x, int y, int nx, int ny) {
        int[] before = board2[x][y];
        int[] next = board2[nx][ny];

        int beforeFromIdx = getIdx(num, before);
        int beforeToIdx = getEmptyIdx(before) - 1;
        if(beforeToIdx == -2) {
            beforeToIdx = 2;
        }

        int nextIdx = getEmptyIdx(next);
        if(nextIdx == -1) {
            System.out.println(turn);
            System.exit(0);
        }
        nextIdx += (beforeToIdx - beforeFromIdx);
        if(nextIdx == 3) {
            System.out.println(turn);
            System.exit(0);
        }

        for(int idx=beforeFromIdx; idx<=beforeToIdx; idx++) {

            int curNum = board2[x][y][idx];
            pieces[curNum].x = nx;
            pieces[curNum].y = ny;

            board2[nx][ny][nextIdx] = board2[x][y][idx];
            board2[x][y][idx] = 0;
            nextIdx--;
        }
    }

    private static void blue(int num, int x, int y) {
        pieces[num].dir += 2;
        pieces[num].dir %= 4;

        int curDir = pieces[num].dir;
        int nx = x + DX[curDir];
        int ny = y + DY[curDir];

        if(nx < 0 || ny < 0 || nx >= size || ny >= size) {
            return;
        }

        if(board[nx][ny] == 0) {
            white(num, pieces[num].x, pieces[num].y, nx, ny);

        } else if(board[nx][ny] == 1) {
            red(num, pieces[num].x, pieces[num].y, nx, ny);
        }
    }

    private static int getIdx(int target, int[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static int getEmptyIdx(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static class Piece {
        int dir;
        int x;
        int y;

        public Piece(int dir, int x, int y) {
            this.dir = dir;
            this.x = x;
            this.y = y;
        }
    }
}