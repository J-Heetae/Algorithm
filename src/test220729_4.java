import java.util.Arrays;

public class test220729_4 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

        int[] solution = solution(array, commands);

        System.out.println(" = " + Arrays.toString(solution));
    }

    public static int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];

        for(int i  = 0; i < commands.length; i++) {

            int[] newArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(newArr);


            answer[i] = newArr[commands[i][2] - 1];
            }
        return answer;
    }
}
