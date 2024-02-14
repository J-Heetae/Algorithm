import java.util.HashSet;
import java.util.Set;

class Solution {
    static final int PRIME = 31;
    static Set<Integer> list = new HashSet<>();
    static boolean[] isChecked;

    public int solution(String[] user_id, String[] banned_id) {
        isChecked = new boolean[user_id.length];
        find(user_id, banned_id, 0);
        return list.size();
    }

    private void find(String[] user_id, String[] banned_id, int idx) {
        if (idx == banned_id.length) {
            int hash = 0;
            for (int i = 0; i < user_id.length; i++) {
                if (isChecked[i])
                    hash = hash * PRIME + 1;
                else
                    hash = hash * PRIME;
            }
            list.add(hash);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (isChecked[i])
                continue;

            if (isMatch(user_id[i], banned_id[idx])) {
                isChecked[i] = true;
                find(user_id, banned_id, idx + 1);
                isChecked[i] = false;
            }
        }
    }

    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length())
            return false;

        for (int i = 0; i < banned.length(); i++) {
            if (banned.charAt(i) == '*')
                continue;

            if (banned.charAt(i) != user.charAt(i))
                return false;
        }
        return true;
    }
}