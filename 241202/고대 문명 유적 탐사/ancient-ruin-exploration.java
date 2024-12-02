import java.util.*;

public class Main {

    final static int N = 5;
    final static int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};

    static int[][] map;
    static int[] spare;
    static int spareIdx;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        spare = new int[m];
        for(int i=0; i<m; i++) {
            spare[i] = sc.nextInt();
        }

        while(k-- > 0) {
            int totalFind = 0;

            //최대 회전 찾기
            int find = getMaxRotate();
            if(find == 0) {
                break;
            } else {
                totalFind += find;
            }

            //연쇄 유물 획득 ( + 빈자리 채우기)
            FindResult findResult = find(map);
            while(findResult.count != 0) {
                totalFind += findResult.count;
                erase(findResult.findMap);
                fill();
                findResult = find(map);
            }
            System.out.print(totalFind + " ");
        }
    }

    static int getMaxRotate() {
        int maxCount = 0;
        int[][] maxFindMap = null;
        int[][] maxRotatedMap = null;

        //순회하면서 최대 위치와 각도 찾기
        for(int d=2; d<=6; d+=2) {
            for(int j=1; j<=3; j++) {
                for(int i=1; i<=3; i++) {
                    int[][] rotatedMap = rotate(i, j, d);
                    FindResult findResult = find(rotatedMap);
                    if(findResult.count > maxCount) {
                        maxCount = findResult.count;
                        maxFindMap = findResult.findMap;
                        maxRotatedMap = rotatedMap;
                    }
                }
            }
        }

        if(maxCount == 0) {
            return 0;
        }
        
        map = maxRotatedMap;

        //유물 획득
        erase(maxFindMap);

        //빈자리 채우기
        fill();

        return maxCount;
    }

    static void fill() {
        for(int j=0; j<N; j++) {
            for(int i=N-1; i>=0; i--) {
                if(map[i][j] == 0) {
                    map[i][j] = spare[spareIdx++];
                }
            }
        }
    }

    static void erase(int[][] findMap) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(findMap[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
    }

    static int[][] rotate(int x, int y, int d) {
        int[][] rotatedMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                rotatedMap[i][j] = map[i][j];
            }
        }

        for(int i=0; i<8; i++) {
            int rotateX = x + DX[i];
            int rotateY = y + DY[i];

            int originX = x + DX[(8 + i - d) % 8];
            int originY = y + DY[(8 + i - d) % 8];

            rotatedMap[rotateX][rotateY] = map[originX][originY];
        }
        return rotatedMap;
    }

    static class FindResult {
        int count;
        int[][] findMap;

        FindResult(int count, int[][] findMap) {
            this.count = count;
            this.findMap = findMap;
        }
    }

    static FindResult find(int[][] targetMap) {
        int[][] visited = new int[N][N];
        int totalCount = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j] == 0) {
                    int curr = targetMap[i][j];
                    
                    Queue<int[]> que = new LinkedList<>();
                    List<int[]> list = new ArrayList<>();
                    
                    que.offer(new int[]{i, j});
                    list.add(new int[]{i, j});
                    visited[i][j] = 1;

                    int count = 1;
                    while(!que.isEmpty()) {
                        int[] poll = que.poll();
                        for(int d=0; d<8; d+=2) {
                            int nx = poll[0] + DX[d];
                            int ny = poll[1] + DY[d];
                            
                            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] != 0 || targetMap[nx][ny] != curr) {
                                continue;
                            }

                            count++;
                            que.offer(new int[]{nx, ny});
                            list.add(new int[]{nx, ny});
                            visited[nx][ny] = 1;
                        }
                    }

                    if(count >= 3) {
                        totalCount += count;
                        for(int[] coor : list) {
                            visited[coor[0]][coor[1]] = 2;
                        }
                    }
                }
            }
        }
        return new FindResult(totalCount, visited);
    }    
}