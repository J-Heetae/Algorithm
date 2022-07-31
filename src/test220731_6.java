import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class test220731_6 {

    public static void main(String[] args) {
        int[] answers = {1, 3, 2, 4, 2};

        int[] solution = solution_2(answers);

        System.out.println("solution = " + Arrays.toString(solution));
    }

    public static int[] solution(int[] answers) {

        int[] first_pattern = {1, 2, 3, 4, 5};
        int[] second_pattern = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third_pattern = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] first = new int[answers.length];
        int[] second = new int[answers.length];
        int[] third = new int[answers.length];

        int[] correct_counter = {0, 0, 0};

        List<Integer> temp = new ArrayList<>();

        for(int i=0; i<answers.length; i++) {
            first[i] = first_pattern[i % first_pattern.length];
            second[i] = second_pattern[i % second_pattern.length];
            third[i] = third_pattern[i % third_pattern.length];
        }


        for(int i=0; i<answers.length; i++) {
            if(answers[i] == first[i]) correct_counter[0]++;
            if(answers[i] == second[i]) correct_counter[1]++;
            if(answers[i] == third[i]) correct_counter[2]++;
        }

        List<Integer> list = Arrays.stream(correct_counter)
                .boxed()
                .collect(Collectors.toList());

        int max = Collections.max(list).intValue();

        for (int i = 0; i < correct_counter.length; i++) {
            if(correct_counter[i] >= max) temp.add(i + 1);
        }

        int[] answer = temp.stream().mapToInt(i->i).toArray();
        return answer;
    }

    public static int[] solution_2(int[] answers) {

        //패턴들 2차원 배열로 만들어 for문 최적화
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        int[] score = {0, 0, 0};

        // 답안 배열 새로 안만들고 패턴이랑 정답을 바로 비교해 score에 저장
        for(int i=0; i< score.length; i++) {
            for(int j=0; j<answers.length; j++) {
                if(patterns[i][j % patterns[i].length] == answers[j]) score[i]++;
            }
        }

        // 리스트를 만들어 Collections.max 방식 Math.max()로 대체
        int max = Math.max(score[0], Math.max(score[1], score[2]));

        // 수포자를 list에 담아 정렬 해결
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < score.length; i++) {
            if(score[i] == max) temp.add(i + 1);
        }

        int[] answer = new int[temp.size()];
        int cnt = 0;

        //List<Integer> 타입도 int 타입으로 향상된 for문 가능
        for (int num: temp) {
            answer[cnt++] = num;
        }

        return answer;
    }
}
