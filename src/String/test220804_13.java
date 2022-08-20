package String;

import java.util.ArrayList;
import java.util.List;

public class test220804_13 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 6, 7};
        int answer = solution(nums);
        System.out.println("answer = " + answer);
    }

    //소수 만들기
    public static int solution(int[] nums) {
        int answer = 0;
        List<Integer> dupl = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int temp = nums[i] + nums[j] + nums[k];
                    if (!dupl.contains(temp)) {
                        boolean flag = false;
                        for (int p = 2; p < temp; p++) {
                            if(temp % p ==0) {
                                flag = true;
                            }
                        }
                        if(!flag) {
                            answer++;
                            dupl.contains(temp);
                        }
                    }
                }
            }
        }
        return answer;
    }

    //최적화(중복검사 제거, 소수 확인을 메서드로)
    public static int solution2(int[] nums) {
        int answer = 0;

        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {

                    int sum = nums[i] + nums[j] + nums[k]; //직관적인 네이밍 temp -> sum

                    if(isPrime(sum)) answer++;
                }
            }
        }

        return answer;
    }

    //소수 확인 메서드
    public static boolean isPrime(int num) {
        boolean flag = true;

        for (int p = 2; p < num; p++){
            if(num % p ==0){
                flag = false;
                break; //break문을 넣어서 불필요한 루프 줄임
            }
        }
        return flag;
    }
}
