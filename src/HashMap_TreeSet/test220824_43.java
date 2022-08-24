package HashMap_TreeSet;

import java.util.*;

public class test220824_43 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stu = sc.nextInt();
        char answer = ' ';
        int max = 0;
        String str = sc.next();
        HashMap<Character, Integer> hm = new HashMap<>();

        for (char c : str.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }

        for(char key : hm.keySet()) {
            if(hm.get(key) > max) {
                max = hm.get(key);
                answer = key;
            }
        }

        System.out.print(answer);
    }
}
