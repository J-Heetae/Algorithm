import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        int[] cnt = new int[n + 1];
        int num = 0;
        char prev = words[0].charAt(0);
        for (; num < words.length; num++) {
            if (wordSet.contains(words[num]) || words[num].charAt(0) != prev) {
                break;
            }
            prev = words[num].charAt(words[num].length() - 1);
            cnt[num % n]++;
            wordSet.add(words[num]);
        }
        return (wordSet.size() == words.length) ? new int[] {0, 0} : new int[] {num % n + 1, cnt[num % n] + 1};
    }
}