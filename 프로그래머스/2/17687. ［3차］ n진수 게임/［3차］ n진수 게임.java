class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String solution = s.solution(2, 4, 2, 1);
        System.out.println(solution);
    }

    public String solution(int n, int t, int m, int p) {
        int till = p + m * t;
        String str = "";
        int number = 0;
        while (str.length() < till) {
            str += Integer.toString(number++, n);
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0, idx = p - 1; i < t; i++, idx += m) {
            answer.append(str.charAt(idx));
        }
        return answer.toString().toUpperCase();
    }
}