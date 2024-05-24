import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution(new int[][] {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
            new int[][] {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}});

        // sol.solution(new int[][] {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}}, new int[][] {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}});
    }

    final int[] DX = {0, 1, 0, -1};
    final int[] DY = {1, 0, -1, 0};

    int n, answer;
    int[][] board, table;
    boolean[][] made, insert;
    ArrayList<Piece> pieceList = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        this.board = game_board;
        this.table = table;
        made = new boolean[n][n];
        insert = new boolean[n][n];

        //퍼즐 조각 만들기
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (made[row][col] || table[row][col] == 0)
                    continue;
                makePiece(row, col);
            }
        }
        //퍼즐 조각 끼우기
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (insert[row][col] || board[row][col] == 1)
                    continue;
                insertPiece(row, col);
            }
        }

        return answer;
    }

    void insertPiece(int x, int y) {
        Result result = getResult(x, y);

        //조각 하나씩 보면서 비교
        for (int i = 0; i < pieceList.size(); i++) {
            Piece piece = pieceList.get(i);
            int[][] form = piece.form;

            if (result.emptySize != piece.size) //사이즈 안맞으면 패스
                continue;

            if (match(result.maxRow, result.minRow, form, result.maxCol, result.minCol)) { //일치하는 경우
                answer += result.emptySize;
                pieceList.remove(i); //현재 조각 제거
                return;
            }
        }
    }

    Result getResult(int x, int y) {
        int emptySize = 0;
        int minRow = x, minCol = y, maxRow = x, maxCol = y;

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x, y});
        insert[x][y] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();

            emptySize++;
            minRow = Math.min(minRow, cur[0]);
            minCol = Math.min(minCol, cur[1]);
            maxRow = Math.max(maxRow, cur[0]);
            maxCol = Math.max(maxCol, cur[1]);

            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = cur[0] + DX[i];
                ny = cur[1] + DY[i];

                if ((nx < 0 || nx >= n || ny < 0 || ny >= n) ||
                    board[nx][ny] == 1 ||
                    insert[nx][ny])
                    continue;
                insert[nx][ny] = true;
                que.offer(new int[] {nx, ny});
            }
        }
        Result result = new Result(emptySize, minRow, minCol, maxRow, maxCol);
        return result;
    }

    class Result {
        int emptySize;
        int minRow, minCol, maxRow, maxCol;

        public Result(int emptySize, int minRow, int minCol, int maxRow, int maxCol) {
            this.emptySize = emptySize;
            this.minRow = minRow;
            this.minCol = minCol;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }
    }

    boolean match(int maxRow, int minRow, int[][] form, int maxCol, int minCol) {
        boolean isMatch = false;

        if ((maxRow - minRow + 1) == form.length && (maxCol - minCol + 1) == form[0].length) {
            isMatch = true;
            outer:
            for (int pr = 0, br = minRow; br <= maxRow; pr++, br++) {
                for (int pc = 0, bc = minCol; bc <= maxCol; pc++, bc++) {
                    if (form[pr][pc] == board[br][bc]) {
                        isMatch = false;
                        break outer;
                    }
                }
            }
            if (isMatch) return true;

            isMatch = true;
            outer:
            for (int pr = form.length - 1, br = minRow; br <= maxRow; pr--, br++) {
                for (int pc = form[0].length - 1, bc = minCol; bc <= maxCol; pc--, bc++) {
                    if (form[pr][pc] == board[br][bc]) {
                        isMatch = false;
                        break outer;
                    }
                }
            }
            if (isMatch) return true;
        }

        if ((maxRow - minRow + 1) == form[0].length && (maxCol - minCol + 1) == form.length) {
            isMatch = true;
            outer:
            for (int pc = 0, br = minRow; br <= maxRow; pc++, br++) {
                for (int pr = form.length - 1, bc = minCol; bc <= maxCol; pr--, bc++) {
                    if (form[pr][pc] == board[br][bc]) {
                        isMatch = false;
                        break outer;
                    }
                }
            }
            if (isMatch) return true;

            isMatch = true;
            outer:
            for (int pc = form[0].length - 1, br = minRow; br <= maxRow; pc--, br++) {
                for (int pr = 0, bc = minCol; bc <= maxCol; pr++, bc++) {
                    if (form[pr][pc] == board[br][bc]) {
                        isMatch = false;
                        break outer;
                    }
                }
            }
            if (isMatch) return true;
        }
        return false;
    }

    void makePiece(int x, int y) {
        int size = 0;
        int minRow = x, minCol = y, maxRow = x, maxCol = y;

        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {x, y});
        made[x][y] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();

            size++;
            minRow = Math.min(minRow, cur[0]);
            minCol = Math.min(minCol, cur[1]);
            maxRow = Math.max(maxRow, cur[0]);
            maxCol = Math.max(maxCol, cur[1]);

            int nx, ny;
            for (int i = 0; i < 4; i++) {
                nx = cur[0] + DX[i];
                ny = cur[1] + DY[i];

                if ((nx < 0 || nx >= n || ny < 0 || ny >= n) ||
                    table[nx][ny] == 0 ||
                    made[nx][ny])
                    continue;

                made[nx][ny] = true;
                que.offer(new int[] {nx, ny});
            }
        }

        int[][] form = new int[maxRow - minRow + 1][maxCol - minCol + 1];
        for (int cr = 0, or = minRow; or <= maxRow; cr++, or++) { // cr = CopyRow, or = OriginRow
            for (int cc = 0, oc = minCol; oc <= maxCol; cc++, oc++) { // cc = CopyCol, oc = OriginCol
                form[cr][cc] = table[or][oc]; //복사
            }
        }
        pieceList.add(new Piece(size, form));
    }

    class Piece {
        int size;
        int[][] form;

        public Piece(int size, int[][] form) {
            this.size = size;
            this.form = form;
        }
    }
}