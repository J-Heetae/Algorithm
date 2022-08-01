import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;

public class test220802_8 {

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {4, 2};
        int[] reserve = {3, 5};

        int solution = solution2(n, lost, reserve);
        System.out.println("solution = " + solution);
    }

    public static int solution(int n, int[] lost, int[] reserve) {

        List<Integer> lost_list = duplicateRemover(lost, reserve);
        List<Integer> reserve_list = duplicateRemover(reserve, lost);
        List<Integer> temp = new ArrayList<>();

        Collections.sort(lost_list);
        Collections.sort(reserve_list);


        System.out.println("new lost_list = " + lost_list);
        System.out.println("new reserve_list = " + reserve_list);



        if(lost_list.size() ==0) {
            return n;
        } else if(reserve_list.size() == 0) {
            return n-lost_list.size();
        } else {
            int k = 0;
            for(int l : lost_list) {
                for(int r : reserve_list) {
                        if ((l + 1 == r) || (l - 1 == r)) {
                            temp.add(r);
                            k++;
                            break;
                        }
                }
            }
            System.out.println("temp = " + temp);
            System.out.println("k = " + k);
            System.out.println("n = " + n);
            return n - (lost_list.size() - k);
        }
    }

    public static List<Integer> duplicateRemover(int[] arr, int[] remove_arr) {

        List<Integer> result = new ArrayList<>();

        for (int a : arr) {
            boolean contains = false;

            for (int r : remove_arr) {
                if(a == r) contains = true;
            }

            if(!contains) result.add(a);
        }

        return result;
    }

    public static int solution2(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] hasCloth = new int[n];

        for (int l : lost)
            hasCloth[l - 1]--;
        for (int r : reserve)
            hasCloth[r - 1]++;

        for (int i = 0; i < hasCloth.length; i++) {
            if(hasCloth[i] == -1) {
                if (i - 1 >= 0 && hasCloth[i - 1] == 1) {
                    hasCloth[i]++;
                    hasCloth[i - 1]--;
                } else if (i + 1 < hasCloth.length && hasCloth[i + 1] == 1) {
                    hasCloth[i]++;
                    hasCloth[i + 1]--;
                } else
                    answer--;
            }
        }
        return answer;
    }
}