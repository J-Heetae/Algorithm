import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {

    static int itemNum, limit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] read = br.readLine().split(" ");

        itemNum = stoi(read[0]);
        limit = stoi(read[1]);

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        read = br.readLine().split(" ");
        for(int i=0; i<itemNum; i++) {
            if(i < itemNum/2) list1.add(stoi(read[i]));
            else list2.add(stoi(read[i]));
        }

        ArrayList<Integer> sum1 = new ArrayList<>();
        ArrayList<Integer> sum2 = new ArrayList<>();

        //각 list의 sum 구하기
        dfs(0, 0, list1, sum1);
        dfs(0, 0, list2, sum2); 

        Collections.sort(sum2); //이분탐색을 위한 오름차순 정렬
        int cnt = 0;
        for (int num : sum1) { //sum1의 모든 요소 순회
            int findNum = limit - num; //추가로 담을 수 있는 무게
            cnt += binarySearch(findNum, sum2) + 1; //경우의 수 추가
        }
        //출력
        System.out.println(cnt);
    }

    //조합할 수 있는 무게까지의 인덱스를 찾아서 리턴
    private static int binarySearch(int findNum, ArrayList<Integer> sum) {
        int left = 0, right = sum.size() - 1, mid, find = -1;
        while(left <= right) {
            mid = (left + right) / 2;
            if (findNum >= sum.get(mid)) {
                find = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //가능한 범위까지의 인덱스를 리턴
        return find;
    }

    //list 안에서 가방에 담을 수 있는 모든 무게의 경우의 수를 sum에 담는 메서드
    private static void dfs(int idx, int weight, ArrayList<Integer> list, ArrayList<Integer> sum) {
        if(weight > limit) return; //최대 무게보다 무거우면 리턴
        if(idx == list.size()) { //모두 담았으면
            sum.add(weight); //추가
            return;
        }
        dfs(idx + 1, weight, list, sum); //현재 물건을 담지 않고 계속
        dfs(idx + 1, weight + list.get(idx), list, sum); //현재 물건을 담고 계속
    }

    //String을 int로 리턴해주는 메서드
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}