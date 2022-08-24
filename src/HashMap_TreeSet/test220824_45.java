package HashMap_TreeSet;

import java.util.*;

public class test220824_45 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        ArrayList<Integer> answer = new ArrayList<>();
        int lt = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<m-1; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        for(int rt=m-1; rt<n; rt++) {
            map.put(arr[rt], map.getOrDefault(arr[rt], 0) + 1);
            answer.add(map.size());
            map.put(arr[lt], map.getOrDefault(arr[lt], 0) - 1);
            if(map.get(arr[lt]) == 0) map.remove(arr[lt]);
            lt++;
        }
        for(int i : answer) {
            System.out.print(i + " ");
        }
    }
}
