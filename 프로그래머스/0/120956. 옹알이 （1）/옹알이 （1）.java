class Solution {

    final String[] WORDS = {"aya", "ye", "woo", "ma"};

    public int solution(String[] babbling) {
        int answer = 0;

        for (String s : babbling) {
            int idx = 0;
            boolean matched = true;
            boolean[] used = new boolean[4];
            while (idx < s.length() && matched) {
                matched = false;
                for (int i = 0; i < 4; i++) {
                    if (used[i])
                        continue;
                    String word = WORDS[i];
                    int length = word.length();

                    if (length > s.length() - idx)
                        continue;

                    if (s.substring(idx, idx + length).equals(word)) {
                        matched = true;
                        idx = idx + length;
                        used[i] = true;
                    }
                }
            }
            if (idx == s.length()) {
                answer++;
            }
        }

        return answer;
    }
}