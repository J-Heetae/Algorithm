package KAKAO_TECH_INTERNSHIP_2022;

import java.util.HashMap;

public class Test_1_221017 {
    HashMap<Character, Integer> hm = new HashMap<>();

    public String solution(String[] survey, int[] choices) {


        //해시맵 초기화
        for (String s : survey) {
            for (char c : s.toCharArray()) {
                hm.put(c, 0);
            }
            if(hm.size() == 8) break;
        }

        //점수 매기기
       for(int i=0; i<survey.length; i++) {
            if(choices[i] < 4) {
                char c = survey[i].charAt(0);
                int abs = Math.abs(choices[i] - 4);

                hm.put(c, hm.get(c) + abs);
            } else if(choices[i] > 4) {
                char c = survey[i].charAt(1);
                int abs = Math.abs(choices[i] - 4);

                hm.put(c, hm.get(c) + abs);
            }
        }

        return getPersonalityType(hm);
    }

    private String getPersonalityType(HashMap<Character, Integer> hm) {

        StringBuilder str = new StringBuilder();

        //점수 비교
        str.append(compareTo('R', 'T'));
        str.append(compareTo('C', 'F'));
        str.append(compareTo('J', 'M'));
        str.append(compareTo('A', 'N'));

        return str.toString();
    }

    private char compareTo(char a, char b) {
        int aNum = hm.getOrDefault(a, 0);
        int bNum = hm.getOrDefault(b, 0);

        if(aNum > bNum) {
            return a;
        } else if(aNum < bNum) {
            return b;
        }

        if(((int)a - (int)b) > 0) {
            return b;
        } else {
            return a;
        }
    }

}
