import java.util.*;

public class Main {
    static final int MAX_HEIGHT = 1_000_000; // 산의 최대 높이
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();

        SegmentTree segTree = new SegmentTree(MAX_HEIGHT + 1); // 세그먼트 트리 생성
        List<Integer> heights = new ArrayList<>(); // 산의 높이 저장
        List<Integer> prefix = new ArrayList<>(); // 각 산의 prefix 값 저장

        while (Q-- > 0) {
            int command = sc.nextInt();

            if (command == 100) { // 초기화 명령어
                int n = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    int h = sc.nextInt();
                    heights.add(h);
                    int maxPrefix = segTree.query(0, h - 1); // h보다 작은 높이의 최대 prefix 값
                    int currentPrefix = maxPrefix + 1; // 현재 산의 prefix 값
                    prefix.add(currentPrefix);
                    segTree.update(h, currentPrefix); // 세그먼트 트리에 갱신
                }
            } else if (command == 200) { // 산 추가
                int h = sc.nextInt();
                heights.add(h);
                int maxPrefix = segTree.query(0, h - 1);
                int currentPrefix = maxPrefix + 1;
                prefix.add(currentPrefix);
                segTree.update(h, currentPrefix);
            } else if (command == 300) { // 산 제거
                int h = heights.remove(heights.size() - 1); // 마지막 산의 높이
                prefix.remove(prefix.size() - 1); // 마지막 prefix 제거
                // 제거는 세그먼트 트리에는 반영하지 않음 (읽기 전용 쿼리이므로 영향을 주지 않음)
            } else if (command == 400) { // 등산 시뮬레이션
                int cableCarIndex = sc.nextInt() - 1; // 케이블카 위치 (0-based)
                int cableCarPrefix = prefix.get(cableCarIndex);
                int totalScore = (cableCarPrefix + segTree.query(0, MAX_HEIGHT)) * 1_000_000
                        + segTree.query(0, MAX_HEIGHT);
                sb.append(totalScore).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    static class SegmentTree {
        int[] tree; // 세그먼트 트리 배열
        int size;

        SegmentTree(int size) {
            this.size = size;
            tree = new int[size * 4]; // 세그먼트 트리의 크기는 입력 크기의 약 4배
        }

        // 구간 [start, end]에서의 최대값 쿼리
        int query(int start, int end) {
            return query(1, 0, size - 1, start, end);
        }

        private int query(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
            // 범위 밖인 경우
            if (queryRight < nodeLeft || queryLeft > nodeRight) {
                return 0; // 최소값
            }

            // 범위 안인 경우
            if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
                return tree[node];
            }

            // 겹친 경우
            int mid = (nodeLeft + nodeRight) / 2;
            return Math.max(
                    query(node * 2, nodeLeft, mid, queryLeft, queryRight),
                    query(node * 2 + 1, mid + 1, nodeRight, queryLeft, queryRight)
            );
        }

        // 특정 위치의 값을 업데이트
        void update(int pos, int value) {
            update(1, 0, size - 1, pos, value);
        }

        private void update(int node, int nodeLeft, int nodeRight, int pos, int value) {
            // 범위 밖인 경우
            if (pos < nodeLeft || pos > nodeRight) {
                return;
            }

            // 리프 노드인 경우
            if (nodeLeft == nodeRight) {
                tree[node] = Math.max(tree[node], value);
                return;
            }

            // 내부 노드인 경우
            int mid = (nodeLeft + nodeRight) / 2;
            update(node * 2, nodeLeft, mid, pos, value);
            update(node * 2 + 1, mid + 1, nodeRight, pos, value);

            // 현재 노드 값 갱신
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }
}
