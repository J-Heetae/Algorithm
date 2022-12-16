package baekjoon.string;

import java.util.*;

public class Test_1157 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase();
        HashMap<Character, Integer> map = new HashMap<>();

        //해시맵에 존재하면 +1 없으면 추가
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //최대값 구하기
        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            if(value > max) max = value;
        }

        //최대값을 가지는 키 구하기
        ArrayList<Character> keys = new ArrayList<>();
        for (Character c : map.keySet()) {
            if(map.get(c) == max) keys.add(c);
        }

        //키가 2개 이상이면 "?" 출력 1개면 키 출력
        if(keys.size() > 1) {
            System.out.print("?");
        } else {
            System.out.println(keys.get(0));
        }
    }
}
