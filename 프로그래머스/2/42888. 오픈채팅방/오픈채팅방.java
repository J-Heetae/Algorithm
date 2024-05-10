import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    HashMap<String, String> nicknameMap = new HashMap<>();

    public String[] solution(String[] record) {
        ArrayList<String> uidList = new ArrayList<>();
        ArrayList<Boolean> inOutList = new ArrayList<>();
        String[] split;
        for (String one : record) {
            split = one.split(" ");
            switch (split[0]) {
                case "Enter":
                    uidList.add(split[1]);
                    inOutList.add(true);
                    nicknameMap.put(split[1], split[2]);
                    break;
                case "Leave":
                    uidList.add(split[1]);
                    inOutList.add(false);
                    break;
                case "Change":
                    nicknameMap.put(split[1], split[2]);
            }
        }
        String[] answer = new String[uidList.size()];
        for (int i = 0; i < answer.length; i++)
            answer[i] = inOut(uidList.get(i), inOutList.get(i));
        return answer;
    }

    String inOut(String uid, boolean in) {
        return (in) ? nicknameMap.get(uid) + "님이 들어왔습니다." : nicknameMap.get(uid) + "님이 나갔습니다.";
    }
}