import java.util.*;
import java.io.*;

public class Main {
    
    static class Rabbit{
        int id;
        int x;
        int y;
        int jump;
        int d;
        long score;

        Rabbit(int id, int x, int y, int d) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.d = d;
            this.jump = 0;
            this.score = 0L;
        }

        // @Override
        // public int compareTo(Rabbit o) {
        //     if(this.jump == o.jump) {
        //         if((this.x + this.y) == (o.x + o.y)) {
        //             if(this.x == o.x) {
        //                 if(this.y == o.y) {
        //                     return this.id - o.id;
        //                 }
        //                 return this.y - o.y;
        //             }
        //             return this.x - o.x;
        //         }
        //         return (this.x + this.y) - (o.x + o.y);
        //     }
        //     return this.jump - o.jump;
        // }   
    }

    static int N, M;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static HashMap<Integer, Rabbit> rabbitMap = new HashMap<>();
    static PriorityQueue<Rabbit> pq = new PriorityQueue<>(new Comparator<Rabbit>() {
        @Override
        public int compare(Rabbit o1, Rabbit o2) {
            if(o1.jump == o2.jump) {
                if((o1.x + o1.y) == (o2.x + o2.y)) {
                    if(o1.x == o2.x) {
                        if(o1.y == o2.y) {
                            return o1.id - o2.id;
                        }
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return (o1.x + o1.y) - (o2.x + o2.y);
            }
            return o1.jump - o2.jump;
        }
    });
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());
            if(query == 100) {
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                for(int i=0; i<P; i++) {
                    int id = Integer.parseInt(st.nextToken());
                    int d = Integer.parseInt(st.nextToken());
                    Rabbit rabbit = new Rabbit(id, 1, 1, d);
                    rabbitMap.put(id, rabbit);
                    pq.offer(rabbit);
                }

            } else if (query == 200) {
                int K = Integer.parseInt(st.nextToken());
                int S = Integer.parseInt(st.nextToken());
                race(K, S);

            } else if (query == 300) {
                int id = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                rabbitMap.get(id).d *= L;

            } else if (query == 400) {
                long maxScore = 0L;
                for(int id : rabbitMap.keySet()) {
                    Rabbit curr = rabbitMap.get(id);
                    maxScore = Math.max(maxScore, curr.score);
                }
                System.out.println(maxScore);
            }

            // for(int id : rabbitMap.keySet()) {
            //     Rabbit curr = rabbitMap.get(id);
            //     System.out.println(curr.id + " " + curr.x + " " + curr.y + " " + curr.score + " " + curr.jump + " " + curr.d);
            // }
            // System.out.println("==========");
        }
    }

    static void race(int K, int S) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<int[]> scoreList = new ArrayList<>();

        for(int i=0; i<K; i++) {
            Rabbit poll = pq.poll();
            poll.jump++;
            set.add(poll.id);
            
            int mx = -1;
            int my = -1;
            
            for(int dir=0; dir<4; dir++) {
                int nx = poll.x + ((poll.d % (N * 2 - 2)) * dx[dir]);
                int ny = poll.y + ((poll.d % (M * 2 - 2)) * dy[dir]);

                nx = fix(nx, N);
                ny = fix(ny, M);

                if((nx + ny) > (mx + my)) {
                    mx = nx;
                    my = ny;
                } else if((nx + ny) == (mx + my)) {
                    if(nx > mx) {
                        mx = nx;
                        my = ny;
                    } else if (nx == mx) {
                        if(ny > my) {
                            my = ny;
                        }
                    }
                }
            }
            poll.x = mx;
            poll.y = my;
            scoreList.add(new int[]{poll.id, (mx + my)});
            pq.offer(poll);

            // System.out.println(poll.id + " " + poll.x + " " + poll.y);
            // System.out.println("=======");
        }

        for(int id : rabbitMap.keySet()) {
            Rabbit curr = rabbitMap.get(id);
            for(int[] s : scoreList) {
                if(curr.id == s[0]) {
                    continue;
                }
                curr.score += s[1];
            }
        }

        ArrayList<Rabbit> temp = new ArrayList<>();
        for(int s : set) {
            temp.add(rabbitMap.get(s));
        }
        Collections.sort(temp, (o1, o2) ->  {
            if((o1.x + o1.y) == (o2.x + o2.y)) {
                if(o1.x == o2.x) {
                    return o2.y - o1.y;
                }
                return o2.x - o1.x;
            }
            return (o2.x + o2.y) - (o1.x + o1.y);
        });
        temp.get(0).score += S;
    }

    static int fix(int n, int size) {
        int fixed = n;

        if(fixed <= 0) {
            fixed = 1 + (-fixed + 1);
            if(fixed > size) {
                fixed = size - (fixed - size);
            }
        } else if(fixed > size) {
            fixed = size - (fixed - size);
            if(fixed <= 0) {
                fixed = 1 + (-fixed + 1);
            }
        }
        return fixed;
    }
}