import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] classes = new int[N][2];

        for(int i = 0; i < N; i++) {
            classes[i][0] = sc.nextInt();
            classes[i][1] = sc.nextInt();
        }
        Arrays.sort(classes, (a,b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int room = 1;
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        for(int[] c : classes) {
            if(endTime.isEmpty() || endTime.peek() > c[0]) {
                if(room <= endTime.size()) {
                    room++;
                }
                endTime.offer(c[1]);
                continue;
            }
            while(!endTime.isEmpty() && endTime.peek() <= c[0]) {
                endTime.poll();
            }
            endTime.offer(c[1]);
        }
        System.out.println(room);
    }
}