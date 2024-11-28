import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<List<Integer>> mount = new ArrayList<>(); // LIS 배열
    static List<Integer> index = new ArrayList<>(); // 각 산의 그룹 인덱스
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt(); // 명령어 개수

        while (Q-- > 0) {
            int command = sc.nextInt();

            if (command == 100) { // 초기화 명령
                int n = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    add(sc.nextInt());
                }
            } else if (command == 200) { // 산 추가
                add(sc.nextInt());
            } else if (command == 300) { // 산 제거
                remove();
            } else if (command == 400) { // 점수 계산
                simulate(sc.nextInt());
            }
        }

        System.out.print(sb.toString());
    }

    // 이분 탐색으로 삽입할 위치 찾기
    static int search(int h) {
        int left = 0, right = mount.size() - 1, idx = mount.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            // mount[mid] 그룹의 마지막 값과 비교
            if (h <= mount.get(mid).get(mount.get(mid).size() - 1)) {
                idx = mid;
                right = mid - 1; // 더 왼쪽 확인
            } else {
                left = mid + 1; // 더 오른쪽 확인
            }
        }

        return idx;
    }

    // 산 추가
    static void add(int height) {
        int idx = search(height);

        index.add(idx); // 현재 산의 그룹 인덱스 저장

        if (idx == mount.size()) {
            mount.add(new ArrayList<>()); // 새로운 그룹 생성
        }

        mount.get(idx).add(height); // 그룹에 높이 추가
    }

    // 산 제거
    static void remove() {
        int idx = index.remove(index.size() - 1); // 마지막 산의 그룹 인덱스 추출
        List<Integer> group = mount.get(idx);

        group.remove(group.size() - 1); // 그룹에서 마지막 산 제거

        if (group.isEmpty()) { // 그룹이 비면 제거
            mount.remove(mount.size() - 1);
        }
    }

    // 점수 계산
    static void simulate(int mIdx) {
        mIdx--; // 0-based index
        int groupIndex = index.get(mIdx);

        // 점수 계산 공식
        int score = (groupIndex + mount.size()) * 1_000_000 + mount.get(mount.size() - 1).get(0);
        sb.append(score).append("\n");
    }
}
