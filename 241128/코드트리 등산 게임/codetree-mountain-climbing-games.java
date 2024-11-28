
import java.util.*;

public class Main {

    static final int MAX = 1_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt() - 1;

        Map map = new Map();
        sc.nextInt();
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            map.add(sc.nextInt());
        }

        while(Q-- > 0) {
            int order = sc.nextInt();

            switch(order) {
                case 200 : map.add(sc.nextInt());
                    break;
                case 300 : map.remove();
                    break;
                case 400 : map.simulation(sc.nextInt());
                    break;
            }
        }

    }

    static class Map {
        class Mountain {
            int height;
            int prefix;
            Mountain next;
            Mountain prev;

            Mountain(int height) {
                this.height = height;
                this.prefix = 0;
                this.next = null;
                this.prev = null;
            }
        }

        Mountain head;
        Mountain tail;
        int size;

        Map() {
            Mountain newMountain = new Mountain(MAX);
            this.head = newMountain;
            this.tail = newMountain;
            this.size = 0;
        }

        void add(int height) {
            Mountain newMountain = new Mountain(height);
            newMountain.prev = tail;
            tail.next = newMountain;
            tail = newMountain;
            size++;

            Mountain compare = tail.prev;
            for(int i=0; i<size - 1; i++) {
                if(compare.height < tail.height) {
                    tail.prefix = Math.max(tail.prefix, compare.prefix + 1);
                }
                compare = compare.prev;
            }
        }

        void remove() {
            Mountain newTail = tail.prev;
            newTail.next = null;
            tail = newTail;
            size--;
        }

        Mountain get(int idx) {
            Mountain curr = head;
            for(int i=0; i<idx; i++) {
                curr = curr.next;
            }
            return curr;
        }

        void simulation(int cableCarIdx) {
            long totalScore = 0L;
            Mountain cableCar = get(cableCarIdx);
            int cableCarPrefix = cableCar.prefix;
            totalScore += (long)cableCarPrefix * MAX;
            totalScore += MAX;

            int maxPrefix = -1;
            Mountain maxMountain = head;
            Mountain curr = head;
            for(int i=0; i<size; i++) {
                curr = curr.next;
                if(maxPrefix == curr.prefix) {
                    if(maxMountain.height < curr.height) {
                        maxMountain = curr;
                    }
                } else if (maxPrefix < curr.prefix) {
                    maxPrefix = curr.prefix;
                    maxMountain = curr;
                }
            }
            System.out.println(totalScore + maxPrefix * MAX + maxMountain.height);
        }
    }
}