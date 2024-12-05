import java.util.*;

public class Main {

    static class Room {
        int parent;
        int power;
        boolean block;
        int count;
        int[] pass;

        Room() {
            this.pass = new int[21];
        }
    }

    static int N;
    static Room[] rooms;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int Q = sc.nextInt();

        rooms = new Room[N + 1];
        for(int i=1; i<=N; i++) {
            rooms[i] = new Room();
        }

        while(Q-- > 0) {
            int order = sc.nextInt();

            if(order == 100) {
                init(sc);
            }
            
            if(order == 200) {
                int c = sc.nextInt();
                changeBlock(c);
            }

            if(order == 300) {
                int c = sc.nextInt();
                int power = sc.nextInt();
                changePower(c, power);
            }

            if(order == 400) {
                int c1 = sc.nextInt();
                int c2 = sc.nextInt();
                changeParent(c1, c2);
            }

            if(order == 500) {
                int c = sc.nextInt();
                System.out.println(rooms[c].count);
            }
        }
    }

    static void changeParent(int c1, int c2) {
        int[] roomArr = {c1, c2};

        for(int i=0; i<2; i++) {
            if(!rooms[roomArr[i]].block) {
                changeBlock(roomArr[i]);
                rooms[roomArr[i]].block = false;
            }
        }

        int tmp = rooms[c1].parent;
        rooms[c1].parent = rooms[c2].parent;
        rooms[c2].parent = tmp;

        for(int i=0; i<2; i++) {
            if(!rooms[roomArr[i]].block) {
                rooms[roomArr[i]].block = true;
                changeBlock(roomArr[i]);
            }
        }
    }

    static void changePower(int c, int power) {
        int[] powerArr = {rooms[c].power, power};
        int[] calArr = {-1, 1};
        rooms[c].power = power;

        if(rooms[c].block) {
            return;
        }

        for(int i=0; i<2; i++) {
            rooms[c].pass[powerArr[i]] += calArr[i];

            int parent = rooms[c].parent;
            for(int j=powerArr[i]-1; j>=0; j--) {
                if(parent == 0) {
                    break;
                }
                Room curr = rooms[parent];
                curr.count += calArr[i];
                if(j > 0) {
                    curr.pass[j] += calArr[i];
                }
                if(curr.block) {
                    break;
                }
                parent = curr.parent;
            }
        }
    }

    static void changeBlock(int c) {
        boolean toggle = (rooms[c].block) ? false : true;
        int mul = (rooms[c].block) ? 1 : -1;

        Room start = rooms[c];
        int parent = start.parent;
        
        int depth = 1;
        while(parent != 0) {
            for(int i=depth; i<=20; i++) {
                rooms[parent].count += mul * (start.pass[i]);
                rooms[parent].pass[i - depth] += mul * (start.pass[i]);
            }
            depth++;
            if(rooms[parent].block) {
                break;
            }
            parent = rooms[parent].parent;
        }
        start.block = toggle;
    }

    static void init(Scanner sc) {
        for(int i=1; i<=N; i++) {
            rooms[i].parent = sc.nextInt();
        }

        for(int i=1; i<=N; i++) {
            int power = sc.nextInt();
            rooms[i].power = (power > 20) ? 20 : power; 
        }

        for(int i=1; i<=N; i++) {
            int parent = rooms[i].parent;
            int power = rooms[i].power;
            rooms[i].pass[power]++;

            for(int j=power - 1; j>=0; j--) {
                if(parent == 0) {
                    break;
                }
                rooms[parent].count++;
                if(j > 0) {
                    rooms[parent].pass[j]++;
                }
                parent = rooms[parent].parent;
            }
        }
    }
}