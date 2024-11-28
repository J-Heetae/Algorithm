import java.util.*;
import java.io.*;

public class Main {

    static class Mountain {
        ArrayList<ArrayList<Integer>> groupList;
        ArrayList<Integer> groupIdxList;

        Mountain() {
            groupList = new ArrayList<>();
            groupIdxList = new ArrayList<>();
        }

        void init(StringTokenizer st) {
            int n = Integer.parseInt(st.nextToken());
            for(int i=0; i<n; i++) {
                int height = Integer.parseInt(st.nextToken());
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

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Mountain mountain = new Mountain();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            if(order == 100) {
                mountain.init(st);
            }
            if(order == 200) {
                mountain.add(Integer.parseInt(st.nextToken()));
            }
            if(order == 300) {
                mountain.remove();
            }
            if(order == 400) {
                sb.append(mountain.getMaxScore(Integer.parseInt(st.nextToken())))
                    .append("\n");
            }
        }
        System.out.println(sb);
    }   
}