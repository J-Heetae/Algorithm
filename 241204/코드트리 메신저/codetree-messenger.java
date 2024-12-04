import java.util.*;

public class Main {

    static class Node {
        int parent;
        int power;
        List<Integer> childs;
        boolean off;

        Node() {
            this.childs = new ArrayList<>();
            this.off = false;
        }

        void onOff() {
            this.off = (this.off) ? false : true;
        }
     }

    static Node[] nodes;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();

        nodes = new Node[N + 1];
        for(int i=1; i<=N; i++) {
            nodes[i] = new Node();
        }
        
        while(Q-- > 0) {
            int order = sc.nextInt();

            if(order == 100) {
                for(int i=1; i<=N; i++) {
                    int parent = sc.nextInt();
                    nodes[i].parent = parent;
                    if(parent != 0) {
                        nodes[parent].childs.add(i);
                    }
                }
                for(int i=1; i<=N; i++) {
                    nodes[i].power = sc.nextInt();
                }
            }

            if(order == 200) {
                int c = sc.nextInt();
                nodes[c].onOff();
            }

            if(order == 300) {
                int c = sc.nextInt();
                int power = sc.nextInt();
                nodes[c].power = power;
            }

            if(order == 400) {
                int c1 = sc.nextInt();
                int c2 = sc.nextInt();
                
                int parent1 = nodes[c1].parent;
                int parent2 = nodes[c2].parent;
                
                nodes[c1].parent = parent2;
                nodes[c2].parent = parent1;
                
                if(parent1 != 0) {
                    for(int i=nodes[parent1].childs.size() - 1; i>=0; i--) {
                        if(nodes[parent1].childs.get(i) == c1) {
                            nodes[parent1].childs.remove(i);
                            break;
                        }
                    }
                    nodes[parent1].childs.add(c2);
                }

                if(parent2 != 0) {
                    for(int i=nodes[parent2].childs.size() - 1; i>=0; i--) {
                        if(nodes[parent2].childs.get(i) == c2) {
                            nodes[parent2].childs.remove(i);
                            break;
                        }
                    }
                    nodes[parent2].childs.add(c1);
                }
            }

            if(order == 500) {
                int c = sc.nextInt();
                System.out.println(find(c, 0));
            }
        }
    }
    
    static int find(int c, int depth) {
        Node curr = nodes[c];
        if(curr.off && depth != 0) {
            return 0;
        }
        int totalCount = ((depth >= 1) && (curr.power >= depth))? 1 : 0;
        for(int child : curr.childs) {
            totalCount += find(child, depth + 1);
        }
        return totalCount;
    }
}