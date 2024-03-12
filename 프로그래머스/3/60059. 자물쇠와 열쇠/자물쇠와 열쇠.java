import java.util.ArrayList;

class Solution {

    static int keyLength, lockLength;
    static int[] standard;
    static ArrayList<int[]> another = new ArrayList<>();

    public boolean solution(int[][] key, int[][] lock) {
        keyLength = key.length;
        lockLength = lock.length;

        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                if (lock[i][j] == 0) {
                    if (standard == null) {
                        standard = new int[] {i, j};
                    } else {
                        another.add(new int[] {i - standard[0], j - standard[1]});
                    }
                }
            }
        }

        if(standard == null) {
            return true;
        }

        for (int repeat = 0; repeat < 4; repeat++) {
            for (int i = 0; i < keyLength; i++) {
                for (int j = 0; j < keyLength; j++) {
                    // if (key[i][j] == 1 && findShape(key, i, j))
                    if (key[i][j] == 1 && findShape(key, i, j) && isMathch(key, lock, i, j))
                        return true;
                }
            }
            key = rotate90Clockwise(key);
        }
        return false;
    }

    private boolean isMathch(int[][] key, int[][] lock, int x, int y) {
        int[][] copy = new int[100][100];

        int[] gap = new int[] {standard[0] - x, standard[1] - y};

        for (int i = 0; i < keyLength; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (key[i][j] == 1) {
                    int nx = i + gap[0];
                    int ny = j + gap[1];

                    if (nx >= lock.length || nx < 0 || ny >= lock.length || ny < 0) {
                        continue;
                    }

                    copy[nx][ny] = 1;
                }
            }
        }
        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                if (lock[i][j] == 1 && copy[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    private boolean findShape(int[][] key, int x, int y) {
        for (int[] coor : another) {
            int nx = x + coor[0];
            int ny = y + coor[1];

            if (nx >= keyLength || nx < 0 || ny >= keyLength || ny < 0 || key[x + coor[0]][y + coor[1]] != 1) {
                return false;
            }
        }
        return true;
    }

    private int[][] rotate90Clockwise(int[][] origin) {
        int[][] copy = new int[keyLength][keyLength];

        for (int i = 0, row = 0; i < keyLength; i++, row++) {
            for (int j = keyLength - 1, col = 0; j >= 0; j--, col++) {
                copy[row][col] = origin[j][i];
            }
        }
        return copy;
    }
}