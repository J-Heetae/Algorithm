package String;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test220804_14 {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        int result = solution2(n, k);

        System.out.println("result = " + result);
    }

//    public static int solution(int n, int k) {
//        String num = Integer.toString(n, k);
//        System.out.println("num = " + num);
//
//
//        if(fourth_regex(num) ==1)
//            return 1;
//
//        else  return first_regex(num)
//                    + second_regex(num)
//                    + third_regex(num);
//    }
//
//    public static int first_regex(String num) {
//        int result = 0;
//        String regex = "0[1-9]+0";
//        List<String> match_num = new ArrayList<>();
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(num);
//        int cnt = 0;
//
//        while(m.find()) {
//            String match = num.substring(m.start() + 1, m.end() - 1);
//
//            if(!match.contains("0")) {
//                match_num.add(num.substring(m.start() + 1, m.end() - 1));
//                cnt++;
//            }
//        }
//
//
//        for(String nums : match_num) {
//            System.out.println("nums = " + nums);
//            if(isPrime(Integer.parseInt(nums))) result++;
//        }
//
//        return result;
//    }
//    public static int second_regex(String num) {
//        int result = 0;
//        String regex = "^[1-9]+0";
//        List<String> match_num = new ArrayList<>();
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(num);
//        int cnt = 0;
//
//        while(m.find()) {
//            String match = num.substring(m.start(), m.end() - 1);
//
//            if(!match.contains("0")) {
//                match_num.add(num.substring(m.start(), m.end() - 1));
//                cnt++;
//            }
//        }
//
//        for(String nums : match_num) {
//            System.out.println("nums = " + nums);
//
//            if(isPrime(Integer.parseInt(nums))) result++;
//        }
//
//        return result;
//    }
//    public static int third_regex(String num) {
//        int result = 0;
//        String regex = "0[1-9]+$";
//        List<String> match_num = new ArrayList<>();
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(num);
//        int cnt = 0;
//
//        while(m.find()) {
//            String match = num.substring(m.start() + 1, m.end());
//
//            if(!match.contains("0")) {
//                match_num.add(num.substring(m.start() + 1, m.end()));
//                cnt++;
//            }
//        }
//
//        for(String nums : match_num) {
//            System.out.println("nums = " + nums);
//
//            if(isPrime(Integer.parseInt(nums))) result++;
//        }
//
//        return result;
//    }
//
//    public static int fourth_regex(String num) {
//        String regex = "[1-9]+";
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(num);
//
//        if(m.matches())
//            if (isPrime(Integer.parseInt(num)) == true) return 1;
//        return 0;
//    }

    // 너무 큰 숫자를 돌리면 런타임 에러가 발생하기 때문에 타입을 long으로 두고 for문 길이를 i*i로 둔다.
    public static int isPrime(long num) {
        if(num <= 1) return 0;
        for(long i = 2; i*i <= num; i++)
            if(num % i == 0) return 0;
        return 1;
    }

    public static int solution2(int n, int k) {
        String[] nums = Integer.toString(n, k).split("0+");
        int answer = 0;
        for(String num : nums) {
            Long temp = Long.valueOf(num);
            answer += isPrime(temp);
        }
        return answer;
    }
}
