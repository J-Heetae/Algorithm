import java.util.*;

public class Main {
    
    static class Player {
        int num;
        int sx, sy, ex, ey;
        int k;
        int damage;
        boolean die;
        
        Player(int num, int r, int c, int h, int w, int k) {
            this.num = num;
            this.sx = r;
            this.sy = c;
            this.ex = r + h - 1;
            this.ey = c + w - 1;
            this.k = k;

            for(int i=sx; i<=ex; i++) {
                for(int j=sy; j<=ey; j++) {
                    p_map[i][j] = num;
                }
            }
        }
    }

    final static int[] DX = {-1, 0, 1, 0};
    final static int[] DY = {0, 1, 0, -1}; 

    static int N;
    static int[][] map;
    static int[][] p_map;
    static Player[] players;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int L = sc.nextInt();
        int Q = sc.nextInt();

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        p_map = new int[N][N];
        players = new Player[N + 1];
        for(int i=1; i<=L; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int h = sc.nextInt();
            int w = sc.nextInt();
            int k = sc.nextInt();
            Player player = new Player(i, r, c, h, w, k);
            players[i] = player;
        }

        while(Q-- > 0) {
            int num = sc.nextInt();
            int dir = sc.nextInt();
            Set<Integer> effected = possible(num, dir);
            if(effected != null) {
                simulation(num, effected, dir);
            }
        }

        int totalDamage = 0;
        for(int i=1; i<=L ;i++) {
            if(players[i].die) {
                continue;
            }
            totalDamage += players[i].damage;
        }
        System.out.println(totalDamage);
    }
    
    static void simulation(int start, Set<Integer> effected, int dir) {
        for(int num : effected) {
            remove(num);
            Player curr = players[num];
            curr.sx += DX[dir];
            curr.ex += DX[dir];
            curr.sy += DY[dir];
            curr.ey += DY[dir];
        }

        for(int num : effected) {
            if(!move(num, (start != num))) {
                remove(num);
            }    
        }
    }

    static void remove(int num) {
        Player curr = players[num];
        for(int i=curr.sx; i<=curr.ex; i++) {
            for(int j=curr.sy; j<=curr.ey; j++) {
                p_map[i][j] = 0;
            }
        }
    }

    static boolean move(int num, boolean damage) {
        Player curr = players[num];
        for(int i=curr.sx; i<=curr.ex; i++) {
            for(int j=curr.sy; j<=curr.ey; j++) {
                p_map[i][j] = curr.num;
                if(damage && map[i][j] == 1) {
                    curr.damage++;
                    if(curr.damage >= curr.k) {
                        curr.die = true;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static Set<Integer> possible(int num, int dir) {
        if(players[num].die) {
            return null;
        }
        Set<Integer> effected = new HashSet<>();
        effected.add(num);
        Player p = players[num];

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        if(dir == 0) {
            for(int j=p.sy; j<=p.ey; j++) {
                q.offer(new int[]{p.sx, j});
                visited[p.sx][j] = true;
            }
        } else if (dir == 1) {
            for(int i=p.sx; i<=p.ex; i++) {
                q.offer(new int[]{i, p.ey});
                visited[i][p.ey] = true;
            }
        } else if (dir == 2) {
            for(int j=p.sy; j<=p.ey; j++) {
                q.offer(new int[]{p.ex, j});
                visited[p.ex][j] = true;
            }
        } else if (dir == 3) {
            for(int i=p.sx; i<=p.ex; i++) {
                q.offer(new int[]{i, p.sy});
                visited[i][p.sy] = true;
            }
        }

        while(!q.isEmpty()) {
            int[] poll = q.poll();

            for(int i=0; i<4; i++) {
                if(i == (dir + 2) % 4) {
                    continue;
                }
                int nx = poll[0] + DX[i];
                int ny = poll[1] + DY[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
                    if(i == dir) {
                        return null;
                    }
                    continue;
                }
                if(!visited[nx][ny] && p_map[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    effected.add(p_map[nx][ny]);
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return effected;
    }
}