import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] characterArr = new int[N];
        for (int i = 0; i < N; i++) {
            int level = Integer.parseInt(br.readLine());
            characterArr[i] = level;
        }
        Arrays.sort(characterArr);
        int maxTeamLevel = 2;
        int left = characterArr[0];
        int right = characterArr[N - 1] + K;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (possibleLevel(mid, characterArr, K)) {
                maxTeamLevel = Math.max(mid, maxTeamLevel);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(maxTeamLevel);
    }

    static boolean possibleLevel(int targetLevel, int[] characterArr, long levelUpPoint) {
        for (int level : characterArr) {
            if (level < targetLevel) {
                levelUpPoint -= targetLevel - level;
            }
        }
        return levelUpPoint >= 0;
    }
}
