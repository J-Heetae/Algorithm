import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    static List<Integer> numbers = new ArrayList<>();
    static int[] count = new int[10_000];

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read;

        read = br.readLine().split(" ");
        N = Integer.parseInt(read[0]);
        M = Integer.parseInt(read[1]);

        Set<Integer> numberSet = new HashSet<>();

        read = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            int currNumber = Integer.parseInt(read[i]);

            if(!numberSet.contains(currNumber)) {
                numbers.add(currNumber);
                numberSet.add(currNumber);
            }

            count[currNumber]++;
        }
        numbers.sort(Integer::compareTo);

        pickArr(0, new int[M]);

        System.out.println(sb.toString());
    }
    static void addArr(int[] arr) {
        for(int i=0; i<M; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("\n");
    }

    static void pickArr(int arrIdx, int[] arr) {
        if(arrIdx == M) { //수열 완성
            addArr(arr);
            return;
        }

        for(int i=0; i<numbers.size(); i++) {
            int currNumber = numbers.get(i);
            if(count[currNumber] > 0) {
                count[currNumber]--;

                arr[arrIdx] = currNumber;
                if(count[currNumber] == 0) {
                    pickArr(arrIdx + 1, arr);
                } else {
                    pickArr(arrIdx + 1, arr);
                }

                count[currNumber]++;
            }
        }
    }

}