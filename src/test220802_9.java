import java.util.Arrays;
import java.util.HashMap;

public class test220802_9{
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        String answer = "";

        for(int i=0; i<completion.length; i++) {
            if(!participant[i].equals(completion[i])) {
                answer += participant[i];
                break;
            }
        }
        if(answer.equals("")) answer += participant[participant.length -1];

        return answer;
    }

    //HashMap 사용하는 문제였음!
    public String solution2(String[] participant, String[] completion) {
        HashMap<String, Integer> hm = new HashMap<>();
        String answer = "";

        for(String p : participant) { hm.put(p, hm.getOrDefault(p, 0) + 1);}
        for(String c : completion) { hm.put(c, hm.get(c) - 1);}

        for(String key : hm.keySet()) {
            if(!(hm.get(key) == 0)) {
                answer = key;
                break;
            }
        }
        return answer;
    }
}
