package Recursive_Tree_Graph;

import java.util.LinkedList;
import java.util.Queue;

//Tree 말단노드까지의 가장 짧은 경로 (BFS)
public class Test_220920_67 {

    static class Node {
        int data;
        Node lt, rt;
        public Node(int val) {
            data = val;
            lt = rt = null;
        }
    }
    Node root;

    public int DFS (Node root) {
        Queue<Node> Q = new LinkedList<>();
        Q.offer(root);
        int L = 0;
        while(!Q.isEmpty()) {
            int len = Q.size();
            for(int i=0; i<len; i++) {
                Node curPos = Q.poll();
                if(curPos.lt == null && curPos.rt == null) return L;
                if(curPos.lt != null) Q.offer(curPos.lt);
                if(curPos.rt != null) Q.offer(curPos.rt);
            }
            L++;
        }
        return 0;
    }

    public static void main(String[] args) {
        Test_220920_67 tree = new Test_220920_67();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(2);
        tree.root.lt.lt = new Node(3);
        tree.root.lt.rt = new Node(3);
        System.out.println(tree.DFS(tree.root));

    }
}
