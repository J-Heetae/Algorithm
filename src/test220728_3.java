import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test220728_3 {
    public static void main(String[] args) {

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        int[] result = solution(id_list, report, k);
        System.out.println("result = " + Arrays.toString(result));
    }



    public static int[] solution(String[] id_list, String[] report, int k) {

        String[] distinct_list = distinct(report);
        String[][] list = new String[distinct_list.length][2];
        String[] temp = new String[2];

        for (int i = 0; i < distinct_list.length; i++) {
            temp = distinct_list[i].split(" ");
            list[i][0] = temp[0];
            list[i][1] = temp[1];
        }

        List<String> ban_list = new ArrayList<>();

        for (int i = 0; i < id_list.length; i++) {
            int count = 0;

            for (int j = 0; j < distinct_list.length; j++) {
                if (id_list[i].equals(list[j][1])) {
                    count++;
                }
            }

            if(count >= k) {
                if(!ban_list.contains("id_list")) {
                    ban_list.add(id_list[i]);
                }
            }
        }

        List<String> reporter = new ArrayList<>();

        for(int i = 0; i< ban_list.size(); i++) {
            for (int j = 0; j < list.length; j++) {
                if (ban_list.get(i).equals(list[j][1])) reporter.add(list[j][0]);
            }
        }

        int[] result = new int[id_list.length];

        for(int i=0; i< id_list.length; i++) {
            int count = 0;
            for(int j=0; j< reporter.size(); j++) {
                if(id_list[i].equals(reporter.get(j)))
                    count += 1;
            }
            result[i] = count;
        }

        return result;
    }

    static String[] distinct(String[] arr) {
        return Arrays.stream(arr).distinct().toArray(String[]::new);
    }

}
