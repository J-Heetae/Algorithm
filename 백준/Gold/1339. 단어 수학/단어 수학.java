import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        Map<Character, Integer> alphabetMap = getAlphabetMap(words);

        List<Integer> numbers = new ArrayList<>();
        for(Character c : alphabetMap.keySet()) {
            numbers.add(alphabetMap.get(c));
        }

        //내림차순 정렬
        numbers.sort((a,b) -> b - a);

        int maxNumber = 0;
        int multiply = 9;
        for(int n : numbers) {
            maxNumber += n * multiply--;
        }

        System.out.println(maxNumber);
    }

    //문자마다 자릿수 더하기
    static Map<Character, Integer> getAlphabetMap(String[] words) {
        Map<Character, Integer> alphabetMap = new HashMap<>();

        for(String w : words) {
            int length = w.length();
            for(int i=0; i<length; i++) {
                int number = (int)Math.pow(10, length - i - 1);
                alphabetMap.put(w.charAt(i), alphabetMap.getOrDefault(w.charAt(i), 0) + number);
            }
        }
        return alphabetMap;
    }
}