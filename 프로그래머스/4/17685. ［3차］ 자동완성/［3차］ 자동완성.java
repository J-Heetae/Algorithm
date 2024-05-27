import java.util.Arrays;
import java.util.HashMap;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[] {"go", "gone", "guild"});
    }

    public int solution(String[] words) {
        int length = words.length;
        Arrays.sort(words);

        int totalType = 0;
        for (int i = 0; i < length; i++) {
            String cur = words[i];
            String before = (i == 0) ? "" : words[i - 1];
            String after = (i == length - 1) ? "" : words[i + 1];

            int a = compare(cur, before);
            int b = compare(cur, after);

            totalType += Math.max(a, b);
        }
        return totalType;
    }

    int compare(String cur, String next) {
        for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
            if (cur.charAt(j) != next.charAt(j)) {
                return j + 1;
            }
        }
        if (cur.length() < next.length())
            return cur.length();
        return next.length() + 1;
    }
}