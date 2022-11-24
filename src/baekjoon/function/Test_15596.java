package baekjoon.function;

public class Test_15596 {
    long sum(int[] a) {
        long ans = 0;
        int length = a.length;

        for(int i=0; i<length; i++) {
            ans += a[i];
        }

        return ans;
    }
}
