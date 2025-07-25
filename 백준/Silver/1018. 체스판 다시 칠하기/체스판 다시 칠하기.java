import java.util.Scanner;

public class Main {

    static int N, M;
    static boolean[][] originBoard;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        originBoard = new boolean[N][M];

        sc.nextLine();
        for (int i = 0; i < N; i++) {
            char[] read = sc.nextLine().toCharArray();
            for (int j = 0; j < M; j++) {
                originBoard[i][j] = read[j] == 'W';
            }
        }
        sc.close();

        int minPainted = Integer.MAX_VALUE;

        for (int x = 0; x <= N - 8; x++) {
            for (int y = 0; y <= M - 8; y++) {
                for (int start = 0; start <= 1; start++) {
                    boolean[][] newBoard = copyBoard(x, y);
                    boolean startColor = (start == 0);

                    int currPainted = 0;
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (i == 0 && j == 0) {
                                if (newBoard[0][0] != startColor) {
                                    currPainted++;
                                    newBoard[0][0] = !newBoard[0][0];
                                }
                                continue;
                            }

                            if (j == 0) {
                                if (newBoard[i - 1][j] == newBoard[i][j]) {
                                    currPainted++;
                                    newBoard[i][j] = !newBoard[i][j];
                                }
                            } else {
                                if (newBoard[i][j - 1] == newBoard[i][j]) {
                                    currPainted++;
                                    newBoard[i][j] = !newBoard[i][j];
                                }
                            }
                        }
                    }
                    minPainted = Math.min(minPainted, currPainted);
                }
            }
        }
        System.out.println(minPainted);
    }

    static boolean[][] copyBoard(int x, int y) {
        boolean[][] copied = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copied[i][j] = originBoard[x + i][y + j];
            }
        }
        return copied;
    }
}