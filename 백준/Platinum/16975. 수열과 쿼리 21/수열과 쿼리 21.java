import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, treeDepth; //수열의 길이
    static int[] arr; //수열 담을 배열
    static long[] tree; //트리 담을 배열
    static String[] read; //입력 받을 배열
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        n = stoi(br.readLine()); //수열 길이 입력 받기

        //수열 입력 받기
        arr = new int[n + 1];
        read = br.readLine().split(" ");
        for(int idx=1; idx<=n; idx++) {
            arr[idx] = stoi(read[idx - 1]);
        }
        
        //트리 깊이 구해서 트리 담을 배열 만들기
        tree = new long[findTreeDepth()];

        //트리 만들기
        makeEmptyTree(1, n, 1);

        //쿼리 입력받아 수행하기
        int numOfQuery = stoi(br.readLine());
        int from, to, addNum, printIdx;
        for(int i=0; i<numOfQuery; i++) {
            read = br.readLine().split(" ");
            int queryNumber = stoi(read[0]);

            switch (queryNumber) {
                case 1:
                    from = stoi(read[1]);
                    to = stoi(read[2]);
                    addNum = stoi(read[3]);
                    add(1, n, from, to, 1, addNum);
                    break;
                case 2:
                    printIdx = stoi(read[1]);
                    print(1, n, printIdx, 1, 0);
                    break;
            }
        }
        System.out.println(answer);
    }

    //범위에 포함되는 노드를 찾고 addNum 더하기
    private static void add(int start, int end, int from, int to, int curNode, int addNum) {
        if (end < from || to < start) { //범위를 벗어난 경우 리턴
            return;
        }
        if(from <= start && end <= to) { //범위에 포함되는 경우 트리에 addNum 더하기
            tree[curNode] += addNum;
            return;
        }
        //범위에 포함되지 않으면 계속 탐색
        int mid = (start + end) / 2;
        add(start, mid, from, to, curNode * 2, addNum);
        add(mid + 1, end, from, to, curNode * 2 + 1, addNum);
    }

    //트리를 탐색해가며 출력할 idx 찾을때까지 노드의 수
    private static void print(int start, int end, int printIdx, int curNode, long sum) {
        sum += tree[curNode]; //sum에 현재 노드 값 추가

        if(start == end && start == printIdx) { //printIdx를 찾은 경우
            answer.append(sum).append("\n"); //answer에 추가
            return;
        }

        int mid = (start + end) / 2;
        if(mid >= printIdx) {
            print(start, mid, printIdx, curNode * 2, sum);
        } else {
            print(mid + 1, end, printIdx, curNode * 2 + 1, sum);
        }
    }

    //트리 만드는 메서드
    private static void makeEmptyTree(int start, int end, int curNode) {
        if(start == end) {
            tree[curNode] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        makeEmptyTree(start, mid, curNode * 2);
        makeEmptyTree(mid + 1, end, curNode * 2 + 1);
    }

    //입력 받은 수열을 트리로 만들 때 깊이 계산할 메서드
    private static int findTreeDepth() {
        return (int)Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)) + 1);
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}