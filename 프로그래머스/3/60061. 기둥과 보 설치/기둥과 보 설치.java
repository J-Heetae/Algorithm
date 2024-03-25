import java.util.ArrayList;

class Solution {

    static int size;
    static final int PILLAR = 0;
    static final int BEAM = 1;

    static final int REMOVE = 0;
    static final int BUILD = 1;

    static int[][] pillarArr;
    static int[][] beamArr;

    public int[][] solution(int n, int[][] build_frame) {
        size = n;
        pillarArr = new int[size + 1][size + 1];
        beamArr = new int[size + 1][size + 1];

        for (int[] one : build_frame) {
            int x = one[0];
            int y = one[1];
            int type = one[2];
            int work = one[3];

            if (type == PILLAR) {
                if (work == BUILD)
                    buildPillar(x, y);
                if (work == REMOVE)
                    removePillar(x, y);
            }

            if (type == BEAM) {
                if (work == BUILD)
                    buildBeam(x, y);
                if (work == REMOVE)
                    removeBeam(x, y);
            }
        }

        ArrayList<int[]> result = new ArrayList<>();
        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                if (pillarArr[x][y] == 1)
                    result.add(new int[] {x, y, 0});
                if (beamArr[x][y] == 1)
                    result.add(new int[] {x, y, 1});
            }
        }

        return result.stream()
            .sorted((a, b) -> a[0] == b[0] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[0] - b[0])
            .toArray(int[][]::new);
    }

    private void buildPillar(int x, int y) {
        if (validPillar(x, y))
            pillarArr[x][y] = 1;
    }

    private boolean validPillar(int x, int y) {
        //바닥, 기둥 위, 보 한쪽 끝
        return y == 0 ||
            pillarArr[x][y - 1] == 1 ||
            beamArr[x][y] == 1 ||
            (x != 0 && beamArr[x - 1][y] == 1);
    }

    private void removePillar(int x, int y) {
        pillarArr[x][y] = 0;

        //위에 기둥에 있을 때
        if (pillarArr[x][y + 1] == 1) {
            if (!validPillar(x, y + 1)) {
                pillarArr[x][y] = 1;
                return;
            }
        }
        //보에 관한 로직
        if (beamArr[x][y + 1] == 1) {
            if (!validBeam(x, y + 1)) {
                pillarArr[x][y] = 1;
                return;
            }
        }

        if (x == 0)
            return;

        if (beamArr[x - 1][y + 1] == 1) {
            if (!validBeam(x - 1, y + 1)) {
                pillarArr[x][y] = 1;
            }
        }
    }

    private void buildBeam(int x, int y) {
        if (validBeam(x, y))
            beamArr[x][y] = 1;
    }

    private boolean validBeam(int x, int y) {
        //한쪽 끝이 기둥 위, 양쪽 끝이 다른 보와 동시에 연결
        return (pillarArr[x][y - 1] == 1 || pillarArr[x + 1][y - 1] == 1 ||
            ((x != 0 && beamArr[x - 1][y] == 1) && beamArr[x + 1][y] == 1));
    }

    private void removeBeam(int x, int y) {
        beamArr[x][y] = 0;

        //보 위에 기둥이 있는 경우
        if (pillarArr[x][y] == 1) {
            if (!validPillar(x, y)) {
                beamArr[x][y] = 1;
                return;
            }
        }

        if (pillarArr[x + 1][y] == 1) {
            if (!validPillar(x + 1, y)) {
                beamArr[x][y] = 1;
                return;
            }
        }

        //보끼리 이어져 있는 경우
        if (beamArr[x + 1][y] == 1) {
            if (!validBeam(x + 1, y)) {
                beamArr[x][y] = 1;
                return;
            }
        }

        if (x == 0)
            return;

        if (beamArr[x - 1][y] == 1) {
            if (!validBeam(x - 1, y)) {
                beamArr[x][y] = 1;
            }
        }
    }
}