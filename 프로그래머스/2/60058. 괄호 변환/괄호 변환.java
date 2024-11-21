import java.util.*;

class Solution {
    public String solution(String p) {
        return (isProper(p))? p : algorithm(p);
    }
    
    String algorithm(String w) {
        if(w.equals("")) {
            return "";
        }
        
        String[] splitResult = split(w);
        String u = splitResult[0];
        String v = splitResult[1];
        
        if(isProper(u)) {
            return u + algorithm(v);
        } else {
            return "(" + algorithm(v) + ")" + reverse(u.substring(1, u.length() - 1));
        }
    }
    
    String[] split(String w) {
        int left = 0;
        int right = 1;
        while(!isBalanced(w.substring(left, right))) {
            right++;
        }
        String u = w.substring(left, right);
        String v = (right == w.length()) ? "" : w.substring(right, w.length());
        return new String[]{u,v};
    }
    
    boolean isBalanced(String str) {
        int left = 0;
        int right = 0;
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
        }
        return left == right;
    }
    
    boolean isProper(String str) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) == '(') {
                stack.push('(');
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    
    String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<u.length(); i++) {
            if(u.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}