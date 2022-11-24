package baekjoon.function;

public class Test_4673 {
    public static void main(String[] args) {
        selfNumber();
    }

    static void selfNumber() {
        int[] num = new int[10001];
        num[0] = 1;

        for(int i=1; i<=9999; i++) {
            if (i  < 10) {
                num[i + i] = 1;
            } else if (9 < i && i < 100) {
                int a = i / 10;
                int b = i - a * 10;

                num[i + a + b] = 1;
            } else if(99 < i && i < 1000) {
                int a = i / 100;
                int b = i / 10 - a * 10;
                int c = i - a * 100 - b * 10;

                num[i + a + b + c] = 1;
            } else if(999 < i && i < 10000) {
                int a = i / 1000;
                int b = i / 100 - a * 10;
                int c = i / 10 - a * 100 - b * 10;
                int d = i - a * 1000 - b * 100 - c * 10;

                if(i + a + b + c + d <= 10000 ) {
                    num[i + a + b + c + d] = 1;
                }
            }
        }

        for(int i=1; i<num.length; i++) {
            if(num[i] == 0) {
                System.out.println(i);
            }
        }
    }
}
