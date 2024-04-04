class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] solution = s.solution(new String[] {"1110"});
        System.out.println(solution[0]);
    }
    String add = "110";

    public String[] solution(String[] s) {
        for (int j = 0; j < s.length; j++) {
            int count_1 = 0;
            int count_110 = 0;
            StringBuilder sb = new StringBuilder();
            String cur = s[j];
            for (int i = 0; i < s[j].length(); i++) {
                if (cur.charAt(i) == '1') {
                    count_1++;
                    sb.append(cur.charAt(i));

                } else if (count_1 >= 2 && cur.charAt(i) == '0') {
                    count_110++;
                    count_1 -= 2;
                    sb.delete(sb.length() - 2, sb.length());

                } else if (cur.charAt(i) == '0') {
                    count_1 = 0;
                    sb.append(cur.charAt(i));
                }
            }

            for(int i= sb.length() - 1; i>=0; i--) {
                if (sb.charAt(i) == '0') {
                    for (int k = 0; k < count_110; k++)
                        sb.insert(i + 1, add);
                    count_110 = 0;
                }
            }

            if(count_110 != 0) {
                for (int k = 0; k < count_110; k++)
                    sb.insert(0, add);
            }

            s[j] = sb.toString();
        }
        return s;
    }
}