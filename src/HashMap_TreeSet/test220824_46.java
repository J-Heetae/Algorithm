package HashMap_TreeSet;

import java.util.*;

public class test220824_46 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String anagram = sc.next();
        int answer = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        int lt = 0;

        for (char a : anagram.toCharArray()) {
            map2.put(a, map2.getOrDefault(a, 0) + 1);
        }

        for(int i=0; i<anagram.length()-1; i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        for (int rt = anagram.length() - 1; rt < str.length(); rt++) {
            map.put(str.charAt(rt), map.getOrDefault(str.charAt(rt), 0) + 1);

            if (map.equals(map2)) answer++;

            map.put(str.charAt(lt), map.getOrDefault(str.charAt(lt), 0) - 1);

            if (map.get(str.charAt(lt)) <= 0) map.remove(str.charAt(lt));

            lt++;
        }

        System.out.print(answer);
    }
}
