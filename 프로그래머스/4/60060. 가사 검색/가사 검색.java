import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        Map<Integer, List<String>> original = new HashMap<>();
        Map<Integer, List<String>> reverse = new HashMap<>();
        
        for(String w : words) {
            int len = w.length();
            original.computeIfAbsent(len, k -> new ArrayList<>()).add(w);
            reverse.computeIfAbsent(len, k -> new ArrayList<>()).add(new StringBuilder(w).reverse().toString());
        }
        
        for(List<String> l : original.values()) Collections.sort(l);
        for(List<String> l : reverse.values()) Collections.sort(l);
        
        int[] answer = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            String query = queries[i];
            int len = query.length();
            
            if(original.get(len) == null) {
                answer[i] = 0;
                continue;
            }
            
            boolean wildStart = query.charAt(0) == '?';    
            if(wildStart) {
                String reverseQuery = new StringBuilder(query).reverse().toString();    
                String firstStr = reverseQuery.replace('?', 'a');
                String lastStr = reverseQuery.replace('?', 'z');
                
                int from = getFirstByBinarySearch(reverse.get(len), firstStr);
                int to = getLastByBinarySearch(reverse.get(len), lastStr);
                
                answer[i] = to - from;     
            } else {
                String firstStr = query.replace('?', 'a');
                String lastStr = query.replace('?', 'z');
                
                int from = getFirstByBinarySearch(original.get(len), firstStr);
                int to = getLastByBinarySearch(original.get(len), lastStr);
            
                answer[i] = to - from;
            }
        }
        return answer;
    }
    
    int getFirstByBinarySearch(List<String> list, String find) {
        int l = 0;
        int r = list.size() - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(list.get(m).compareTo(find) >= 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    
    int getLastByBinarySearch(List<String> list, String find) {
        int l = 0;
        int r = list.size() - 1;
        while(l <= r) {
            int m = (l + r) / 2;
            if(list.get(m).compareTo(find) > 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}