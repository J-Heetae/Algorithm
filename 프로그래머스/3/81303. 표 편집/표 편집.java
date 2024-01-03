import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
    }
    static int[] pre, next;
    static Stack<Delete> deleted = new Stack<>();
    public String solution(int n, int k, String[] cmd) {
        int idx = k;
        pre = new int[n];
        next = new int[n];

        for(int i=0; i<n; i++) {
            pre[i] = i - 1;
            next[i] = i + 1;
        }
        next[n-1] = -1;

        StringBuilder answer = new StringBuilder();
        for(int i=0; i<n; i++) answer.append("O");
        int num;
        for (String s : cmd) {
            char cur = s.charAt(0);

            if (cur == 'U') {
                num = Integer.parseInt(s.substring(2));
                while (num-- > 0) idx = pre[idx];
            }

            if (cur == 'D') {
                num = Integer.parseInt(s.substring(2));
                while (num-- > 0) idx = next[idx];
            }

            if (cur == 'C') {
                deleted.push(new Delete(pre[idx], idx, next[idx]));

                if(pre[idx] != -1) next[pre[idx]] = next[idx];
                if(next[idx] != -1) pre[next[idx]] = pre[idx];

                answer.setCharAt(idx, 'X');

                if(next[idx] != -1) idx = next[idx];
                else idx = pre[idx];
            }

            if (cur == 'Z') {
                Delete last = deleted.pop();

                if(last.before != -1) next[last.before] = last.cur;
                if(last.after != -1) pre[last.after] = last.cur;

                answer.setCharAt(last.cur, 'O');
            }
        }
        return answer.toString();
    }

    class Delete {
        int before, cur, after;

        public Delete(int before, int cur, int after) {
            this.before = before;
            this.cur = cur;
            this.after = after;
        }
    }
}