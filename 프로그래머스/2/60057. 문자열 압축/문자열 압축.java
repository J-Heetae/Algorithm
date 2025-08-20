class Solution {
    public int solution(String s) {
        int sLength = s.length();
        int minLength = sLength;
        
        for(int cut = 1; cut <= sLength / 2; cut++) { //자르는 길이
            StringBuilder sb = new StringBuilder();
            
            for(int idx = 0; idx < sLength; idx += cut) {
                if(sb.length() >= minLength) {
                    break;
                }
                if(idx + cut <= sLength) {
                    String curr = s.substring(idx, idx + cut);
                    int count = 1;
                    int lastIdx = idx + cut;
                    while(lastIdx + cut <= sLength && curr.equals(s.substring(lastIdx, lastIdx + cut))) {
                        count++;
                        lastIdx += cut;
                    }
                    if(count > 1) {
                        sb.append(count)
                            .append(s.substring(idx, idx + cut));
                        idx = lastIdx - cut;
                        continue;
                    }
                }
                sb.append(s.substring(idx, Math.min(idx + cut, sLength)));
            }
            minLength = Math.min(minLength, sb.length());
        }
        return minLength;
    }
}