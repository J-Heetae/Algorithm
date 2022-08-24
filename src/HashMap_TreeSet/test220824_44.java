package HashMap_TreeSet;

import java.util.*;

//아나그램
public class test220824_44 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        String answer = "YES";
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : str1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str2.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
            } else {
                answer = "NO";
                break;
            }
        }

        for (char c : map.keySet()) {
            if(map.get(c) != 0) {
                answer = "NO";
                break;
            }
        }

        System.out.print(answer);
    }
}
