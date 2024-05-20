import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int n = target.length;
        int[] count = new int[n]; //현재 원소 수
        int[] nextNodeIdx = new int[edges.length + 2]; //다음 자식 노드 인덱스

        ArrayList<Integer>[] edgeList = new ArrayList[edges.length + 2]; //노드의 간선 리스트
        for (int i = 0; i < edgeList.length; i++) { //초기화
            edgeList[i] = new ArrayList<>();
        }

        for (int[] edge : edges) { //간선 리스트 만들기
            edgeList[edge[0]].add(edge[1]);
        }

        //정렬 및 리프 노드 체크
        for (int i = 0; i < edgeList.length; i++) {
            if (edgeList[i].isEmpty()) {
                nextNodeIdx[i] = -1; //인덱스 -1로
            } else {
                Collections.sort(edgeList[i]); //오름차순 정렬
            }
        }

        //노드 순서 구하기
        ArrayList<Integer> nodeOrder = new ArrayList<>();
        while (true) {
            int curNode = 1;
            while (true) {
                int nextIdx = nextNodeIdx[curNode];
                if (nextIdx == -1) { //리프 노드인 경우
                    nodeOrder.add(curNode);
                    count[curNode - 1]++;
                    break;
                } else { //리프 노드가 아닌 경우
                    int nextNode = edgeList[curNode].get(nextIdx); //다음 노드
                    nextNodeIdx[curNode] = (nextIdx + 1) % edgeList[curNode].size(); //간선 바꿔주기
                    curNode = nextNode; //현재 노드 교체
                }
            }
            if (count[curNode - 1] > target[curNode - 1]) { //현재 추가된 노드가 필요없는 노드인 경우
                nodeOrder.remove(nodeOrder.size() - 1); //지우고
                break; //브레이크
            }
        }

        //각 노드마다 필요한 숫자의 최소값
        int[] minNeed = new int[n];
        for(int i = 0; i< n; i++) {
            if(target[i] != 0) {
                minNeed[i] = target[i] / 3 + ((target[i] % 3 != 0) ? 1 : 0);
            }
        }

        //각 노드마다 숫자 몇개 있는지 체크
        int[] nodeCount = new int[n];
        for (Integer i : nodeOrder) {
            nodeCount[i - 1]++;
        }

        //혹시 부족해?
        for(int i=0; i<nodeCount.length; i++) {
            if(minNeed[i] > nodeCount[i]) {
                return new int[] {-1}; //부족하면 return -1
            }
        }

        int max = Integer.MIN_VALUE; //최소한 진행해야 되는 노드 순서
        for(int i=0; i<minNeed.length; i++) {
            int curNeed = minNeed[i];
            int cnt = 0;
            for(int j=0; j<nodeOrder.size(); j++) {
                if(nodeOrder.get(j) == i + 1) {
                    cnt++;
                    if(curNeed == cnt) {
                        max = Math.max(max, j);
                        break;
                    }
                }
            }
        }

        //max까지 각 노드에 숫자 몇개 있는지 다시 체크하고 인덱스 저장
        int[] answer = new int[max + 1]; //여기까지만 진행하면됨
        ArrayList<Integer>[] orderIdx = new ArrayList[n];
        for(int i = 0; i< n; i++) {
            orderIdx[i] = new ArrayList<>();
            for(int j=0; j<=max; j++) {
                if(nodeOrder.get(j) == i + 1) {
                    orderIdx[i].add(j);
                }
            }
        }

        for(int i = 0; i< n; i++) {
            if(orderIdx[i].isEmpty()) { //할당된 숫자 없으면 패스
                continue;
            }

            int needNumber = target[i]; //만들어야 하는 수
            int cnt = orderIdx[i].size(); //가지고 있는 숫자 갯수

            needNumber -= cnt;
            for(int j=orderIdx[i].size()-1; j>=0; j--) {
                int plus = Math.min(needNumber, 2);
                answer[orderIdx[i].get(j)] = 1 + plus;
                needNumber -= plus;
            }
        }
        return answer;
    }
}