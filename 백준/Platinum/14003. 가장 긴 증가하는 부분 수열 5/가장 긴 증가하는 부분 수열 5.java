import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        int[] posIdxArr = new int[N];
        int[] prevIdxArr = new int[N];
        Arrays.fill(prevIdxArr, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int pos = posSearch(numArr, list, numArr[i]);
            if (pos < list.size()) {
                list.set(pos, i);
            } else {
                list.add(i);
            }
            posIdxArr[i] = pos;
            if (pos > 0) {
                prevIdxArr[i] = list.get(pos - 1);
            }
        }

        int listLength = list.size();
        int[] result = new int[listLength];
        int idx = list.get(listLength - 1);

        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = numArr[idx];
            idx = prevIdxArr[idx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.length).append("\n");
        for (Integer i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int posSearch(int[] numArr, ArrayList<Integer> list, int num) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (numArr[list.get(mid)] < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}