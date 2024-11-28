import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<List<Integer>> mount = new ArrayList<>(); // 산 그룹 저장
    static List<Integer> index = new ArrayList<>(); // 각 산의 그룹 인덱스 저장
    static int Q; // 명령어 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Q = sc.nextInt();

        while (Q-- > 0) {
            int o = sc.nextInt(); // 명령어 입력
            if (o == 100) {
                int n = sc.nextInt();
                for (int i = 0; i < n; i++) {
                    int h = sc.nextInt();
                    int idx = search(h);
                    index.add(idx);
                    if (idx == mount.size()) {
                        mount.add(new ArrayList<>());
                    }
                    mount.get(idx).add(h);
                }
            } else if (o == 200) {
                int h = sc.nextInt();
                int idx = search(h);
                index.add(idx);
                if (idx == mount.size()) {
                    mount.add(new ArrayList<>());
                }
                mount.get(idx).add(h);
            } else if (o == 300) {
                int idx = index.remove(index.size() - 1);
                mount.get(idx).remove(mount.get(idx).size() - 1);
                if (mount.get(mount.size() - 1).isEmpty()) {
                    mount.remove(mount.size() - 1);
                }
            } else if (o == 400) {
                int mIdx = sc.nextInt() - 1; // 케이블카 위치 (0-based)
                int groupIndex = index.get(mIdx);
                int score = (groupIndex + mount.size()) * 1_000_000 + mount.get(mount.size() - 1).get(0);
                System.out.println(score);
            }
        }

        sc.close();
    }

    // 이분 탐색으로 삽입 위치를 찾음
    static int search(int h) {
        int start = 0, end = mount.size() - 1;
        int idx = mount.size();

        while (start <= end) {
            int mid = (start + end) / 2;
            if (h <= mount.get(mid).get(mount.get(mid).size() - 1)) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return idx;
    }
}