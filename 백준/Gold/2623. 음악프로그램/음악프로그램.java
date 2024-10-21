import java.util.*;

class Main {
    //메이비 위상정렬
    //보조PD는 신경쓸 필요가 없을 듯?
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int singerNum = sc.nextInt();
        int pdNum = sc.nextInt();
        int[] inArr = new int[singerNum + 1];
        ArrayList<Integer>[] outArr = new ArrayList[singerNum + 1];
        for (int i = 1; i <= singerNum; i++) {
            outArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < pdNum; i++) {
            int num = sc.nextInt();
            int before = 0;
            for (int j = 0; j < num; j++) {
                int cur = sc.nextInt();
                if (before != 0) {
                    inArr[cur]++;
                    outArr[before].add(cur);
                }
                before = cur;
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= singerNum; i++) {
            if (inArr[i] == 0) {
                q.offer(i);
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append("\n");
            cnt++;
            for (int next : outArr[cur]) {
                inArr[next]--;
                if (inArr[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if (cnt != singerNum) {
            System.out.print(0);
        } else {
            System.out.print(sb);
        }
    }
}