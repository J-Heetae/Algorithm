import java.io.*;
import java.util.*;

class Main {
    //상우하좌
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0 , -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] room = new int[N][N];
        int studentNum = (int)(Math.pow(N, 2));


        //좋아하는 학생 입력 받기
        int[] order = new int[studentNum];
        ArrayList<Integer>[] favorite = new ArrayList[studentNum + 1];
        for(int i=1; i<=studentNum; i++){
            favorite[i] = new ArrayList<>();
        }

        for(int i=1; i<=studentNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            order[i - 1] = num;
            for(int j=0; j<4; j++) {
                favorite[num].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] pos = new int[studentNum + 1][2];
        for(int i=1; i<=studentNum; i++) {
            pos[i][0] = -1;
            pos[i][1] = -1;
        }

        //자리 배정하기
        for(int i=0; i<order.length; i++) {
            int[] coor = assign(pos, favorite[order[i]], room, order[i]);
            room[coor[0]][coor[1]] = order[i];
            pos[order[i]][0] = coor[0];
            pos[order[i]][1] = coor[1];
        }

        int totalFine = 0;
        for(int i=1; i<=studentNum; i++) {
            int count = 0;
            for(int j=0; j<4; j++) {
                int nx = pos[i][0] + dx[j];
                int ny = pos[i][1] + dy[j];
                if(nx < 0 || ny < 0 || nx >= room.length ||  ny >= room.length) {
                    continue;
                }
                if(favorite[i].contains(room[nx][ny])) {
                    count++;
                }
            }
            if(count == 1) {
                totalFine += 1;
            } else if (count == 2) {
                totalFine += 10;
            } else if (count == 3) {
                totalFine += 100;
            } else if (count == 4) {
                totalFine += 1000;
            }
        }
        System.out.println(totalFine);
    }

    static int[] assign(int[][] pos, ArrayList<Integer> favorit, int[][] room, int num) {
        ArrayList<int[]> candidate = new ArrayList<>();
        for(int i : favorit) {
            if(pos[i][0] != -1) {
                for(int j=0; j<4; j++) {
                    int nx = pos[i][0] + dx[j];
                    int ny = pos[i][1] + dy[j];
                    if(nx < 0 || ny < 0 || nx >= room.length ||  ny >= room.length || room[nx][ny] != 0) {
                        continue;
                    }
                    candidate.add(new int[]{nx, ny});
                }
            }
        }

        if(candidate.isEmpty()) {
            return getMostEmpty(room);
        } else {
            candidate.sort((x, y) -> {
                if (x[0] == y[0]) {
                    return x[1] - y[1];
                }
                return x[0] - y[0];
            });

            int maxFavorit = 0;
            int maxEmpty = 0;
            int x = 0;
            int y = 0;
            for(int[] coor : candidate) {
                int currFavorit = 0;
                int currEmpty = 0;
                for(int i=0; i<4; i++) {
                    int nx = coor[0] + dx[i];
                    int ny = coor[1] + dy[i];
                    if(nx < 0 || ny < 0 || nx >= room.length ||  ny >= room.length) {
                        continue;
                    }
                    if(room[nx][ny] == 0) {
                        currEmpty++;
                    }
                    if(favorit.contains(room[nx][ny])) {
                        currFavorit++;
                    }
                }
                if(currFavorit > maxFavorit) {
                    maxFavorit = currFavorit;
                    maxEmpty = currEmpty;
                    x = coor[0];
                    y = coor[1];
                } else if (currFavorit == maxFavorit && currEmpty > maxEmpty) {
                    maxEmpty = currEmpty;
                    x = coor[0];
                    y = coor[1];
                }
            }
            return new int[]{x, y};
        }
    }

    static int[] getMostEmpty(int[][] room) {
        int maxEmpty = -1;
        int x = -1;
        int y = -1;
        for(int i=0; i<room.length; i++) {
            for(int j=0; j<room.length; j++) {
                int count = 0;
                if(room[i][j] != 0) {
                    continue;
                }
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || ny < 0 || nx >= room.length ||  ny >= room.length || room[nx][ny] != 0) {
                        continue;
                    }
                    count++;
                }
                if(count == 4) {
                    return new int[]{i,j};
                } else {
                    if(count > maxEmpty) {
                        maxEmpty = count;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return new int[]{x,y};
    }
}