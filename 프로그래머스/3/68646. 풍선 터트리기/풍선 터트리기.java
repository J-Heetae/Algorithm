class Solution {
    public int solution(int[] a) {
        int size = a.length;

        int[] leftMinArr = new int[size];
        int[] rightMinArr = new int[size];

        leftMinArr[0] = a[0];
        rightMinArr[size - 1] = a[size - 1];

        for (int i = size - 2; i > 0; i--)
            rightMinArr[i] = Math.min(rightMinArr[i + 1], a[i]);

        for (int i = 1; i < size; i++)
            leftMinArr[i] = Math.min(leftMinArr[i - 1], a[i]);

        int answer = size;
        for (int i = 1; i < size - 1; i++)
            if (a[i] > leftMinArr[i] && a[i] > rightMinArr[i])
                answer--;
        return answer;
    }
}