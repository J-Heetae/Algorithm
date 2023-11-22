import java.util.HashSet;
import java.util.Set;

class Solution {

    static int totalMatches;
    static boolean[] isChecked;
    static String[] userList;
    static String[] bannedList;
    static Set<Integer> uniqueCases = new HashSet<>();

    public int solution(String[] user_ids, String[] banned_ids) {
        isChecked = new boolean[user_ids.length];
        userList = user_ids;
        bannedList = banned_ids;
        findMatches(banned_ids.length, 0, 0);
        return totalMatches;
    }

    private void findMatches(int length, int passed, int bannedIndex) {
        if (passed == length) {
            StringBuilder caseBuilder = new StringBuilder();
            for (boolean isCheckedUser : isChecked) {
                if (isCheckedUser)
                    caseBuilder.append(1);
                else
                    caseBuilder.append(0);
            }
            if (!uniqueCases.contains(Integer.parseInt(caseBuilder.toString()))) {
                totalMatches++;
                uniqueCases.add(Integer.parseInt(caseBuilder.toString()));
            }
            return;
        }

        for (int userIndex = 0; userIndex < userList.length; userIndex++) {
            if (isChecked[userIndex])
                continue;
            if (matchesPattern(userList[userIndex], bannedList[bannedIndex])) {
                isChecked[userIndex] = true;
                findMatches(length, passed + 1, bannedIndex + 1);
                isChecked[userIndex] = false;
            }
        }
    }

    private boolean matchesPattern(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }

        for (int charIndex = 0; charIndex < bannedId.length(); charIndex++) {
            if (bannedId.charAt(charIndex) == '*')
                continue;
            if (bannedId.charAt(charIndex) != userId.charAt(charIndex)) {
                return false;
            }
        }
        return true;
    }
}