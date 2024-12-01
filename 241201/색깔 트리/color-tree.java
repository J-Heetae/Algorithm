import java.util.*;
import java.io.*;

public class Main {

    static class Tree {
        class Node {
            int mId;
            int pId;
            int color;
            int updated;
            int maxDepth;
            ArrayList<Integer> childs;
            
            Node(int mId, int pId, int color, int updated, int maxDepth) {
                this.mId = mId;
                this.pId = pId;
                this.color = color;
                this.updated = updated;
                this.maxDepth = maxDepth;
                this.childs = new ArrayList<>();
            }
        }

        Map<Integer, Node> map;
        List<Integer> roots;

        Tree() {
            map = new HashMap<>();
            roots = new ArrayList<>();
        }

        void add(int mId, int pId, int color, int updated, int maxDepth) {
            Node node = new Node(mId, pId, color, updated, maxDepth);
            if(pId == -1) {
                map.put(mId, node);
                roots.add(mId);
            } else if (isValid(pId, 1)) {
                map.put(mId, node);
                map.get(pId).childs.add(mId);
            }
        }

        boolean isValid(int mId, int depth) {
            Node node = map.get(mId);
            
            if(node.maxDepth <= depth) {
                return false;
            }
            
            if(node.pId != -1) {
                return isValid(node.pId, depth + 1);
            }
            return true;
        }

        void changeColor(int mId, int color, int updated) {
            Node node = map.get(mId);
            node.color = color;
            node.updated = updated;
        }

        int getColor(int mId) {
            int[] resultColor = updateColor(map.get(mId));
            return resultColor[0];
        }

        private int[] updateColor(Node node) {
            if(node.pId != -1) {
                int[] pResult = updateColor(map.get(node.pId));
                if(pResult[1] > node.updated) {
                    node.color = pResult[0];
                    node.updated = pResult[1];
                }
            }
            return new int[]{node.color, node.updated};
        }

        int getScores() {
            int totalScores = 0;
            for(int rootId : roots) {
                ColorReader result = collectColors(rootId, 0, 0);
                totalScores += result.scores;
            }
            return totalScores;
        }

        ColorReader collectColors(int mId, int pColor, int pUpdated) {
            Node node = map.get(mId);
            if(pUpdated > node.updated) {
                node.updated = pUpdated;
                node.color = pColor;
            }

            ColorReader colorReader = new ColorReader();
            for(int childId : node.childs) {
                ColorReader childColorReader = collectColors(childId, node.color, node.updated);
                colorReader.collect(childColorReader);
            }

            colorReader.colors[node.color] = true;
            int count = 0;
            for(int i=1; i<=5; i++) {
                if(colorReader.colors[i]) {
                    count++;
                }
            }
            colorReader.scores += count * count;
            return colorReader;
        }

        class ColorReader {
            int scores;
            boolean[] colors;
            
            ColorReader() {
                this.scores = 0;
                this.colors = new boolean[6];
            }

            void collect(ColorReader o) {
                this.scores += o.scores;
                for(int i=1; i<=5; i++) {
                    if(o.colors[i]) {
                        this.colors[i] = true;
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Tree tree = new Tree();

        int q = Integer.parseInt(br.readLine());
        for(int i=1; i<=q; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            
            if(order == 100) {
                int mId = Integer.parseInt(st.nextToken());
                int pId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                int maxDepth = Integer.parseInt(st.nextToken());
                tree.add(mId, pId, color, i, maxDepth);
                
            } else if (order == 200) {
                int mId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                tree.changeColor(mId, color, i);

            } else if (order == 300) {
                int mId = Integer.parseInt(st.nextToken());
                sb.append(tree.getColor(mId)).append("\n");

            } else {
                sb.append(tree.getScores()).append("\n");
            }
        }
        System.out.println(sb);
    }
}