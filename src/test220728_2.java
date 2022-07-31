public class test220728_2 {
    public int[] solution(int[] lottos, int[] win_nums) {

        int rank = 7;
        int zero_count = 0;

        for(int l : lottos) {
            if(l==0) ++zero_count;

            for (int w : win_nums) {
                if (l==w) --rank;
            }
        }

        int highst = ((rank - zero_count == 7) ? 6 : rank - zero_count);
        int lowest = ((rank == 7) ? 6 : rank);

        int[] answer = {highst, lowest};

        return answer;
    }
}
