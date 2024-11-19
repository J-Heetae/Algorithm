import java.util.*;

class Solution {
    
    static String[] orders;
    static int[] selected;
    static boolean[][] orderArr;
    static ArrayList<String> answer;
    static ArrayList<String> setList;
    static ArrayList<Integer> cntList;
    
    
    public String[] solution(String[] orders, int[] course) {
        this.orders = orders;
        orderArr = new boolean[orders.length][26];
        answer = new ArrayList<>();
        
        for(int i=0; i<orders.length; i++) {
            String cur = orders[i];
            for(int j=0; j<cur.length(); j++) {
                orderArr[i][cur.charAt(j) - 'A'] = true;
            }
        }
        
        for(int courseNum : course) {
            selected = new int[courseNum];
            setList = new ArrayList<>();
            cntList = new ArrayList<>();
            
            for(int i=0; i<orders.length - 1;  i++) {
                if(orders[i].length() < courseNum) {
                    continue;
                }
                makeSetMenu(i, courseNum, 0, 0);
            }
            
            int maxCnt = 0;
            for(int cnt : cntList) {
                maxCnt = Math.max(maxCnt, cnt);
            }
            if(maxCnt >= 2) { 
                for(int i=0; i<cntList.size(); i++) {
                    if(cntList.get(i) == maxCnt) {
                        answer.add(setList.get(i));
                    }
                }
            }
        }
        Collections.sort(answer);
        String[] result = new String[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
    
    void makeSetMenu(int orderIdx, int setNum, int startIdx, int num) {
        if(num == setNum) {
            ArrayList<Character> selectedList = new ArrayList<>();
            for(int i : selected) {
                selectedList.add((char)(i + 'A'));
            }
            Collections.sort(selectedList);
            
            StringBuilder sb = new StringBuilder();
            for(Character i : selectedList) {
                sb.append(i);
            }
            if(!setList.contains(sb.toString())) {
                setList.add(sb.toString());
                int cnt = 1;
                for(int i=orderIdx + 1; i<orders.length; i++) {
                    boolean flag = true;
                    for(int idx : selected) {
                        if(!orderArr[i][idx]) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        cnt++;
                    }
                }
                cntList.add(cnt);
            }
            return;
        }
        
        if(startIdx >= orders[orderIdx].length()) {
            return;
        }
        
        for(int i=startIdx; i<orders[orderIdx].length(); i++) {
            selected[num] = orders[orderIdx].charAt(i) - 'A';
            makeSetMenu(orderIdx, setNum, i + 1, num + 1);
        }
    }
}