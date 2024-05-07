import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> wordSet = new HashSet<>();
        int[] cnt = new int[n + 1];
        int num = 0;
        for (; num < words.length; num++) {
            if (wordSet.contains(words[num]) || (num != 0 && !words[num].startsWith(String.valueOf(words[num - 1].charAt(words[num - 1].length() - 1))))) {
                break;
            }
            cnt[num % n]++;
            wordSet.add(words[num]);
        }
        return (wordSet.size() == words.length) ? new int[] {0, 0} : new int[] {num % n + 1, cnt[num % n] + 1};
    }
}