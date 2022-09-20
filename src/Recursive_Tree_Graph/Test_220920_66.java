package Recursive_Tree_Graph;

//Tree 말단노드까지의 가장 짧은 경로 (DFS)
//이런 문제는 BFS로 푸는게 효율적이나 공부중이니까 DFS로도
public class Test_220920_66 {

    static class Node {
        int data;
        Node lt, rt;
        public Node(int val) {
            data = val;
            lt = rt = null;
        }
    }
    Node root;

    public int DFS (int L, Node root) {
        if(root.lt == null && root.rt == null) return L;
        else return Math.min(DFS(L + 1, root.lt), DFS(L + 1, root.rt));
    }

    public static void main(String[] args) {
        Test_220920_66 tree = new Test_220920_66();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(2);
        tree.root.lt.lt = new Node(3);
        tree.root.lt.rt = new Node(3);
        System.out.println(tree.DFS(0, tree.root));

    }
}
