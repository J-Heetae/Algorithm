import java.util.Arrays;

class Solution {
    static int answerIdx;
    static int[][] answer;
    static Node root;

    public int[][] solution(int[][] nodeinfo) {

        int[][] newNodeInfo = new int[nodeinfo.length][3];
        for(int i=0; i<nodeinfo.length; i++) {
            newNodeInfo[i][0] = nodeinfo[i][0];
            newNodeInfo[i][1] = nodeinfo[i][1];
            newNodeInfo[i][2] = i + 1;
        }

        Arrays.sort(newNodeInfo, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        root = makeTree(newNodeInfo, 0, 0, 100_000);
        answer = new int[2][nodeinfo.length];
        preorder(root);
        answerIdx = 0;
        postorder(root);

        return answer;
    }

    private void postorder(Node cur) {
        if(cur.left != null) postorder(cur.left);
        if(cur.right != null) postorder(cur.right);
        answer[1][answerIdx++] = cur.nodeNum;
    }

    private void preorder(Node cur) {
        answer[0][answerIdx++] =  cur.nodeNum;
        if(cur.left != null) preorder(cur.left);
        if(cur.right != null) preorder(cur.right);
    }

    private Node makeTree(int[][] nodeinfo, int nodeIdx, int from, int to) {
        Node cur = new Node(nodeinfo[nodeIdx][2]);
        int curNum = cur.nodeNum;

        if (nodeIdx == nodeinfo.length - 1)
            return cur;

        int firstIdx = Integer.MAX_VALUE;
        int lastIdx = nodeinfo.length - 1;

        int nextLevel = -1;
        int mid = nodeinfo[nodeIdx][0];

        for (int i = nodeIdx + 1; i < nodeinfo.length; i++) {
            if (nodeinfo[i][1] == nodeinfo[nodeIdx][1])
                continue;

            if (nextLevel == -1 && nodeinfo[i][1] < nodeinfo[nodeIdx][1]) {
                nextLevel = nodeinfo[i][1];
                firstIdx = i;
                continue;
            }

            if (nodeinfo[i][1] != nextLevel) {
                lastIdx = i - 1;
                break;
            }
        }

        for (int i = firstIdx; i <= lastIdx; i++) {
            int[] next = nodeinfo[i];
            if (next[0] < from || next[0] > to)
                continue;

            if (next[0] < mid)
                cur.left = makeTree(nodeinfo, i, from, mid - 1);
            else
                cur.right = makeTree(nodeinfo, i, mid + 1, to);
        }
        return cur;
    }

    private class Node {
        int nodeNum;
        Node left, right;

        public Node(int nodeNum) {
            this.nodeNum = nodeNum;
        }
    }
}