package HashMap_TreeSet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

//프로그래머스 전화번호 목록 해시
public class Test_221103_1 {

    /**
     * 정렬과 이중 for문 사용
     * 효율성에서 탈락
     */
    /*public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for(int i=0; i<phone_book.length; i++) {
            for(int j=i+1; j<phone_book.length; j++) {
                if(phone_book[j].startsWith(phone_book[i])) return false;
            }
        }
        return true;
    }*/

    public boolean solution(String[] phone_book) {

        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i< phone_book.length; i++) {
            map.put(phone_book[i], i);
        }

        for (String s : phone_book) {
            for(int i=1; i<s.length(); i++) {
                if(map.containsKey(s.substring(0, i))) return false;
            }
        }

        return true;
    }
}
