import java.util.ArrayList;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int edgeNumber = 0;

        Edge[] edgeArr = new Edge[1_000_001];

        for (int[] edge : edges) {
            if (edgeArr[edge[0]] == null) {
                edgeArr[edge[0]] = new Edge();
                edgeNumber++;
            }

            if (edgeArr[edge[1]] == null) {
                edgeArr[edge[1]] = new Edge();
                edgeNumber++;
            }

            edgeArr[edge[0]].outList.add(edge[1]);
            edgeArr[edge[1]].inList.add(edge[0]);
        }

        for (int i = 1; i < edgeArr.length; i++) {
            if (edgeArr[i] != null && edgeArr[i].inList.size() == 0 && edgeArr[i].outList.size() > 1) {
                answer[0] = i;
                break;
            }
        }

        for (int connected : edgeArr[answer[0]].outList) {
            Edge one = edgeArr[connected];

            for (int i = one.inList.size() - 1; i >= 0; i--) {
                if (one.inList.get(i) == answer[0]) {
                    one.inList.remove(i);
                    break;
                }
            }

            //끄트머리 막대 찾기
            if (one.inList.size() == 0) {
                answer[2]++;
                continue;
            }

            //가운데 8찾기
            if (one.inList.size() > 1) {
                answer[3]++;
                continue;
            }

            //도넛 찾기
            int two = one.inList.get(0);

            //하나 있는 도넛인 경우
            if (two == connected) {
                answer[1]++;
                continue;
            }

            //두개 있는 도넛
            if (edgeArr[two].inList.size() == 1 && edgeArr[two].inList.get(0) == connected) {
                answer[1]++;
                continue;
            }
            
            while(true) {
                //찾았다 도넛 요놈
                if(two == connected) {
                    answer[1]++;
                    break;
                }
                
                //막대 찾기
                if (edgeArr[two].inList.size() == 0) {
                    answer[2]++;
                    break;
                }

                //8 찾기
                if (edgeArr[two].inList.size() > 1) {
                    answer[3]++;
                    break;
                }

                two = edgeArr[two].inList.get(0);
            }
        }
        return answer;
    }

    class Edge {
        ArrayList<Integer> inList = new ArrayList<>();
        ArrayList<Integer> outList = new ArrayList<>();
    }
}