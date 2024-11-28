import java.util.*;

public class Main {

    static final int MAX = 1_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt() - 1;

        Map map = new Map();
        sc.nextInt();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            map.add(sc.nextInt());
        }

        while (Q-- > 0) {
            int order = sc.nextInt();

            switch (order) {
                case 200:
                    map.add(sc.nextInt());
                    break;
                case 300:
                    map.remove();
                    break;
                case 400:
                    map.simulation(sc.nextInt());
                    break;
            }
        }
        System.out.println(sb);
    }

    static class Map {
        class Mountain {
            int height;
            int prefix;

            Mountain(int height) {
                this.height = height;
                this.prefix = 0;
            }
        }

        List<Mountain> mountains;
        TreeMap<Integer, Integer> prefixMap; // prefix -> 최대 높이 관리

        Map() {
            mountains = new ArrayList<>();
            prefixMap = new TreeMap<>();
        }

        void add(int height) {
            Mountain newMountain = new Mountain(height);
            int newPrefix = 0;

            // 이전 산들과 비교하여 prefix 계산
            if (!mountains.isEmpty() && mountains.get(mountains.size() - 1).height < height) {
                newPrefix = mountains.get(mountains.size() - 1).prefix + 1;
            }

            newMountain.prefix = newPrefix;
            mountains.add(newMountain);

            // prefixMap 업데이트
            prefixMap.put(newPrefix, Math.max(prefixMap.getOrDefault(newPrefix, 0), height));
        }

        void remove() {
            if (mountains.isEmpty()) return;

            Mountain removedMountain = mountains.remove(mountains.size() - 1);

            // prefixMap에서 제거 또는 업데이트
            if (prefixMap.get(removedMountain.prefix) == removedMountain.height) {
                prefixMap.remove(removedMountain.prefix);
                for (Mountain mountain : mountains) {
                    if (mountain.prefix == removedMountain.prefix) {
                        prefixMap.put(removedMountain.prefix, Math.max(prefixMap.getOrDefault(removedMountain.prefix, 0), mountain.height));
                    }
                }
            }
        }

        void simulation(int cableCarIdx) {
            long totalScore = 0L;
            Mountain cableCar = mountains.get(cableCarIdx - 1);

            // 케이블카 점수
            totalScore += (long) cableCar.prefix * MAX;
            totalScore += MAX;

            // prefixMap에서 최대값 탐색
            int maxPrefix = prefixMap.lastKey();
            int maxHeight = prefixMap.get(maxPrefix);

            totalScore += (long) maxPrefix * MAX + maxHeight;
            sb.append(totalScore).append("\n");
        }
    }
}
