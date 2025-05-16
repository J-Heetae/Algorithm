import java.util.*;

class Solution {
    static final int MAX_NODE = 1_000_000;
    
    //특정 Node가 어떤 그룹에 속해있는지
    int[] group = new int[MAX_NODE + 1];
    //특정 노드와 연결된 노드 리스트
    Map<Integer, List<Integer>> linked = new HashMap<>();
    //특정 그룹의 홀짝트리, 역홀짝 트리 가능 여부
    Map<Integer, int[]> groupResult = new HashMap<>();
    
    public int[] solution(int[] nodes, int[][] edges) {
        for(int node : nodes) {
            group[node] = node;
            linked.put(node, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            
            union(a, b);
            
            linked.get(a).add(b);
            linked.get(b).add(a);
        }
        
        for(int node : nodes) {
            //그룹(트리)이 총 몇개인지 구하기
            int currGroup = find(node);
            if(node == currGroup) {
                groupResult.put(node, new int[2]);
            }
        } 
        
        for(int node : nodes) { //해당 노드를 root 노드로 설정
            int currGroup = find(node);
            
            // idx 0은 홀짝트리, idx 1은 역홀짝트리
            int[] currGroupResult = groupResult.get(currGroup);
            
            int connectedNodeCount = linked.get(node).size();
            
            //0일 경우 홀짝노드 1일 경우 역홀짝노드
            int idx = 0;
            if(node % 2 != connectedNodeCount % 2) {
                idx = 1;
            }
            
            if(currGroupResult[idx] == 0) {
                boolean flag = true;
                for(Integer next : linked.get(node)) {
                    if(!verify(node, next, (idx == 0))) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag) {
                    currGroupResult[idx] = 1;
                }
            }
        }
        
        int[] answer = new int[2];
        // 트리마다 가능한 여부 확인
        for(Integer groupNum : groupResult.keySet()) {
            int[] result = groupResult.get(groupNum);
            
            answer[0] += result[0];
            answer[1] += result[1];
        }
        return answer;
    }
    
    public boolean verify(int prev, int node, boolean straight) {
        int connectedNodeCount = linked.get(node).size() - 1;        
        boolean curr = (node % 2 == connectedNodeCount % 2);

        if(curr != straight) {
            return false;
        }
        
        for(Integer next : linked.get(node)) {
            if(next == prev) {
                continue;
            }
            if(!verify(node, next, straight)) {
                return false;
            }
        }
        return true;
    }
    
    public int find(int node) {
        if(node == group[node]) {
            return node;
        }
        return group[node] = find(group[node]);
    }
    
    public void union(int a, int b) {
        int gA = find(a);
        int gB = find(b);
        
        if(gA != gB) {
            if(gA >= gB) {
                group[gA] = gB;
            } else {
                group[gB] = gA;
            }
        }
    }
}