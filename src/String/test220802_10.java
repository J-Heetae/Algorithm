package String;

import java.util.HashSet;

public class test220802_10 {
    public int solution(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num : nums) {
            hashSet.add(num);
        }

        int answer = (hashSet.size() >= nums.length/2) ? nums.length/2 : hashSet.size();

        return answer;
    }
}
