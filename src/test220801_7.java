public class test220801_7 {

    public static void main(String[] args) {

    }

    public static int solution(int[] numbers) {

        int answer = 0;

        for(int i=0; i<10; i++) {
            boolean contain = false;
            for(int num : numbers) {
                if(i == num) {
                    contain = true;
                    break;
                }
            }
            if(!contain) {
                answer += i;
            }
        }
        return answer;
    }

    public int solution_2(int[] numbers) {
        //0~9까지의 합을 모두 더하고 배열의 값을 빼는 방법
        int sum = 45;
        for(int num : numbers) {
            sum -= num;
        }
        return sum;
    }
}
