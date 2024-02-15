import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[] {"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
    }
    public int[] solution(String[] gems) {
        HashMap<String, Integer> gemMap = new HashMap<>();
        int idx = 0;
        for (String gem : gems) {
            if(!gemMap.containsKey(gem))
                gemMap.put(gem, idx++);
        }

        int gemNumber = gemMap.size();

        int[] gemArr = new int[gems.length];
        for(int i=0; i<gems.length; i++) {
            gemArr[i] = gemMap.get(gems[i]);
        }

        int start = 0;
        int end = 0;

        int left = 0;
        int right = -1;
        int minLength = Integer.MAX_VALUE;

        int[] gemCntArr = new int[gemNumber];
        int cnt = 0;
        while (right < gems.length - 1) {
            right++;
            if(gemCntArr[gemArr[right]] == 0)
                cnt++;
            gemCntArr[gemArr[right]]++;

            while(cnt == gemNumber) {
                if(minLength > right - left) {
                    minLength = right - left;
                    start = left + 1;
                    end = right + 1;
                }
                gemCntArr[gemArr[left]]--;
                if(gemCntArr[gemArr[left]] == 0)
                    cnt--;
                left++;
                if(left == right) break;
            }
        }
        return new int[]{start,end};
    }
}