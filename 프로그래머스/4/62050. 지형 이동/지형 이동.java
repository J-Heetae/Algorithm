import java.util.ArrayList;
import java.util.Collections;

class Solution {
    final int[] DX = {0, 1, 0, -1};
    final int[] DY = {1, 0, -1, 0};

    int n, height, color;
    int[] parent;
    int[][] colorMap, land;
    ArrayList<Ladder> ladderList = new ArrayList<>();

    public int solution(int[][] land, int height) {
        n = land.length;
        this.land = land;
        this.height = height;
        colorMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (colorMap[i][j] == 0) {
                    color++;
                    coloring(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                laddering(i, j);
            }
        }

        parent = new int[color + 1];
        for (int i = 1; i <= color; i++) {
            parent[i] = i;
        }
        int totalCost = 0;
        Collections.sort(ladderList);
        for (Ladder ladder : ladderList) {
            if (find(ladder.from) != find(ladder.to)) {
                union(ladder.from, ladder.to);
                totalCost += ladder.cost;
            }
        }
        return totalCost;
    }

    void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            if (parentA <= parentB) {
                parent[parentB] = parentA;
            } else {
                parent[parentA] = parentB;
            }
        }
    }

    int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    void laddering(int x, int y) {
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + DX[i];
            ny = y + DY[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (colorMap[x][y] != colorMap[nx][ny]) {
                ladderList.add(new Ladder(colorMap[x][y], colorMap[nx][ny], Math.abs(land[x][y] - land[nx][ny])));
            }
        }
    }

    void coloring(int x, int y) {
        colorMap[x][y] = color;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + DX[i];
            ny = y + DY[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            if (colorMap[nx][ny] == 0) {
                if (Math.abs(land[x][y] - land[nx][ny]) <= height) {
                    coloring(nx, ny);
                }
            }
        }
    }

    class Ladder implements Comparable<Ladder> {
        int from, to, cost;

        public Ladder(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Ladder o) {
            return this.cost - o.cost;
        }
    }
}