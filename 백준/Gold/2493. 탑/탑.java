import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());
        int[] heights = new int[N];
        String[] line = sc.nextLine().split(" ");
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(line[i]);
        }

        int[] receive = new int[N];
        Stack<Tower> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            int curHeight = heights[i];
            while (!stack.isEmpty() && stack.peek().height <= curHeight) {
                Tower tower = stack.pop();
                receive[tower.idx] = i + 1;
            }
            stack.push(new Tower(curHeight, i));
        }

        while (!stack.isEmpty()) {
            Tower tower = stack.pop();
            receive[tower.idx] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : receive) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static class Tower {
        int height, idx;

        public Tower(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }
}