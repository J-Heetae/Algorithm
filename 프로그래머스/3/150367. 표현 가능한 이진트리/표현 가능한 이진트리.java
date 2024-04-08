class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new long[] {7, 42, 5});
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binaryNumber = Long.toBinaryString(numbers[i]);
            int mid = binaryNumber.length() / 2;

            boolean possible = false;
            for (int j = 0; j < mid; j++) {
                if (possible)
                    break;

                if (binaryNumber.charAt(j) == '1') {
                    int add = binaryNumber.length() - 1 - (j * 2);

                    StringBuilder sb = new StringBuilder();
                    sb.append("0".repeat(Math.max(0, add)));
                    sb.append(binaryNumber);

                    possible = check(sb.toString());
                }
            }

            if (!possible && binaryNumber.length() % 2 == 1)
                possible = check(binaryNumber);

            answer[i] = possible ? 1 : 0;
        }
        return answer;
    }

    boolean check(String number) {
        int length = number.length();

        if (length == 1) //리프 노드면
            return true;

        if(length % 2 == 0 )
            return false;

        int mid = length / 2;
        if (number.charAt(mid) == '0') { // 현재 트리의 루트 노드가 더미인 경우
            for (char c : number.toCharArray()) {
                if (c == '1')
                    return false;
            }
            return true;
        }

        return check(number.substring(0, mid)) &&
            check(number.substring(mid + 1));
    }
}