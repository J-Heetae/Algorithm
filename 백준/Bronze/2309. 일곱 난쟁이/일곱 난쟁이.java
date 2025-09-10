import java.util.*;

public class Main {

    static boolean finish;
    static int[] heights;
    static int[] picked = new int[7];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        heights = new int[9];
        for (int i = 0; i < 9; i++) {
            heights[i] = sc.nextInt();
        }
        Arrays.sort(heights);

        finish = false;
        combination(0, -1, 0);
    }

    static void combination(int idx, int start, int sum) {
        if (finish) {
            return;
        }

        if (sum > 100) {
            return;
        }

        if (idx == 7) {
            if (sum == 100) {
                for (int i = 0; i < 7; i++) {
                    System.out.println(picked[i]);
                }
                finish = true;
            }
            return;
        }

        for (int i = start + 1; i < 9; i++) {
            picked[idx] = heights[i];
            combination(idx + 1, i, sum + heights[i]);
        }
    }
}