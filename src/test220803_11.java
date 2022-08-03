public class test220803_11 {

    public static void main(String[] args) {

        String s = "one";

        int solution = solution3(s);

        System.out.println("solution = " + solution);

    }

    public static int solution(String s) {

        if(s.contains("zero")) s = s.replaceAll("zero","0");
        if(s.contains("one")) s = s.replaceAll("one","1");
        if(s.contains("two")) s = s.replaceAll("two","2");
        if(s.contains("three")) s = s.replaceAll("three","3");
        if(s.contains("four")) s = s.replaceAll("four","4");
        if(s.contains("five")) s = s.replaceAll("five","5");
        if(s.contains("six")) s = s.replaceAll("six","6");
        if(s.contains("seven")) s = s.replaceAll("seven","7");
        if(s.contains("eight")) s = s.replaceAll("eight","8");
        if(s.contains("nine")) s = s.replaceAll("nine","9");

        int answer = Integer.parseInt(s);
        return answer;
    }

    //배열을 사용해서 중복제거
    public static int solution2(String s) {
        String[] digits = {"0","1","2","3","4","5","6","7","8","9"};
        String[] alphabets = {"zero","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0; i<10; i++) {
            if(s.contains(alphabets[i]))
               s = s.replaceAll(alphabets[i], digits[i]);
        }

        return Integer.parseInt(s);
    }


    //2차원 배열 이용해보기(더 빠르다!!)
    public static int solution3(String s) {
        String[][] digits_alphabets = {{"0", "zero"},
                {"1", "one"},
                {"2", "two"},
                {"3", "three"},
                {"4", "four"},
                {"5", "five"},
                {"6", "six"},
                {"7", "seven"},
                {"8", "eight"},
                {"9", "nine"}};

        //속도 차이는 for문의 차이였음 XX
        //for문의 차이가 아니고 replace와 replaceAll의 차이!
        //if문으로 조건을 걸어주면 미세하게 속도 향상
        for(String[] map : digits_alphabets){
            s = s.replace(map[1], map[0]);
        }

//        for(int i=0; i<10; i++) {
//            if(s.contains(digits_alphabets[i][1]))
//                s = s.replaceAll(digits_alphabets[i][1], digits_alphabets[i][0]);
//        }

        return Integer.parseInt(s);
    }
}
