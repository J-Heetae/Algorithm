import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> numberSet = new HashSet<>();
        for (int length = 1; length <= elements.length; length++) {
            for (int idx = 0; idx < elements.length; idx++) {
                int sum = 0;
                for (int i = 0; i < length; i++) {
                    sum += elements[(idx + i) % elements.length];
                }
                numberSet.add(sum);
            }
        }
        return numberSet.size();
    }
}