class Solution {
    final int MOD = 10_007;

    public int solution(int n, int[] tops) {
        int length = tops.length;
        int[] a = new int[length];
        int[] b = new int[length];

        a[0] = 1;
        if (tops[0] == 1)
            b[0] = 3;
        else
            b[0] = 2;

        for (int i = 1; i < length; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % MOD;
            if (tops[i] == 1)
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % MOD;
            else
                b[i] = (a[i - 1] + b[i - 1] * 2) % MOD;
        }
        return (a[length - 1] + b[length - 1]) % MOD;
    }
}