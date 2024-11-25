import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution("xababcdcdababcdcd");
    }

    static String str;

    public int solution(String s) {
        this.str = s;
        int answer = str.length();
        for(int unit = 1; unit <= str.length() / 2; unit++) {
            answer = Math.min(answer, compression(unit));
        }
        return answer;
    }

    int compression(int unit) {
        StringBuilder sb = new StringBuilder();

        String before = str.substring(0, unit);
        int repeated = 1;

        for(int i = unit; i < str.length(); i += unit) {
            boolean flag = true;
            if(i + unit - 1 >= str.length()) {
                break;
            }
            for(int j = 0; j < unit; j++) {
                if(before.charAt(j) != str.charAt(i + j)) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                repeated++;
            } else {
                if(repeated > 1) {
                    sb.append(repeated);
                }
                sb.append(before);
                before = str.substring(i, Math.min(str.length(), i + unit));
                repeated = 1;
            }
        }
        if(repeated > 1) {
            sb.append(repeated);
        }
        sb.append(before);
        sb.append(str.substring(str.length() - (str.length() % unit)));
        return sb.length();
    }
}