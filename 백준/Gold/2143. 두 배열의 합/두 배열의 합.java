import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine());

        int[] nArr = initArr();
        int[] mArr = initArr();

        int n = nArr.length;
        int m = mArr.length;

        int[] nPrefixArr = getPrefixArr(n, nArr);
        int[] mPrefixArr = getPrefixArr(m, mArr);

        List<Integer> nPrefixList = new ArrayList<>();
        List<Integer> mPrefixList = new ArrayList<>();

        Map<Integer, Long> nCountMap = new HashMap<>();
        Map<Integer, Long> mCountMap = new HashMap<>();

        makeMapAndList(n, nCountMap, nPrefixList, nPrefixArr);
        makeMapAndList(m, mCountMap, mPrefixList, mPrefixArr);

        Collections.sort(nPrefixList);
        Collections.sort(mPrefixList);

        long answer = 0;
        for (int cur : nPrefixList) {
            int left = 0;
            int right = mPrefixList.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int midValue = mPrefixList.get(mid);
                if (midValue + cur == T) {
                    answer += (long)nCountMap.get(cur) * mCountMap.get(midValue);
                    break;
                } else if (midValue + cur < T) {
                    left = mid + 1;
                } else if (midValue + cur > T) {
                    right = mid - 1;
                }
            }
        }
        System.out.println(answer);
    }

    private static int[] getPrefixArr(int n, int[] arr) {
        int[] nPrefixArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nPrefixArr[i] = arr[i - 1] + nPrefixArr[i - 1];
        }
        return nPrefixArr;
    }

    private static int[] initArr() throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] arr = new int[n];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }

    private static void makeMapAndList(int n, Map<Integer, Long> countMap, List<Integer> prefixList,
        int[] prefixArr) {
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int sum = prefixArr[j] - prefixArr[i - 1];
                if (countMap.containsKey(sum)) {
                    countMap.put(sum, countMap.get(sum) + 1);
                } else {
                    countMap.put(sum, 1L);
                    prefixList.add(sum);
                }
            }
        }
    }
}