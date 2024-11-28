import java.util.*;

public class Main {

    static class Mountain {
        ArrayList<ArrayList<Integer>> groupList;
        ArrayList<Integer> groupIdxList;

        Mountain() {
            groupList = new ArrayList<>();
            groupIdxList = new ArrayList<>();
        }

        void init() {
            int n = sc.nextInt();
            for(int i=0; i<n; i++) {
                int height = sc.nextInt();
                add(height);
            }
        }

        void add(int height) {
            int idx = searchIdx(height);
            groupIdxList.add(idx);
            if(idx == groupList.size()) {
                groupList.add(new ArrayList<>());
            }
            groupList.get(idx).add(height);
        }

        int searchIdx(int height) {
            int left = 0;
            int right = groupList.size();
            while(left < right) {
                int mid = (left + right) / 2;
                ArrayList<Integer> group = groupList.get(mid);
                if(height <= group.get(group.size() - 1)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }

        void remove() {
            int groupIdx = groupIdxList.get(groupIdxList.size() - 1);
            groupIdxList.remove(groupIdxList.size() - 1);

            ArrayList<Integer> group = groupList.get(groupIdx);
            group.remove(group.size() - 1);
            if(group.size() == 0) {
                groupList.remove(groupIdx);
            }
        }

        long getMaxScore(int cableCarIdx) {
            int groupIdx = groupIdxList.get(cableCarIdx - 1);
            return (long)((groupIdx) * SCORE) +
                    SCORE + 
                    ((groupList.size() - 1) * SCORE) + 
                    (groupList.get(groupList.size() - 1).get(0));
        }
    }

    final static int SCORE = 1_000_000;
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Mountain mountain = new Mountain();

        int q = sc.nextInt();
        while(q-- > 0) {
            int order = sc.nextInt();
            if(order == 100) {
                mountain.init();
            }
            if(order == 200) {
                mountain.add(sc.nextInt());
            }
            if(order == 300) {
                mountain.remove();
            }
            if(order == 400) {
                sb.append(mountain.getMaxScore(sc.nextInt()))
                    .append("\n");
            }
        }
        System.out.println(sb);
    }   
}