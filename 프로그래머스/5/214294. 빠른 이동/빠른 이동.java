import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

class Solution {
    static final int INF = 987654321;

    static int nodeNum;
    static UnionFind unionFind;
    static Stack<Integer> pathStack = new Stack<>();
    static ArrayList<Integer> rootNodes = new ArrayList<>();
    static int[] stackIdx;
    static boolean[] visited;
    static ArrayList<Integer>[] roadList;
    static HashSet<Integer>[] reachableNodeSet;

    public int solution(int n, int[][] roads) {
        init(n);
        getRoadList(roads);
        unionCycleProcess();
        getReachableNodeSet(roads);
        getRootNodes();
        return rootNodes.size() - matchingProcess() - 1;
    }

    void init(int n) {
        nodeNum = n;
        reachableNodeSet = getHashSet(n + 1);
        unionFind = new UnionFind(n + 1);
        stackIdx = new int[n + 1];
        visited = new boolean[n + 1];
    }

    HashSet<Integer>[] getHashSet(int n) {
        HashSet<Integer>[] set = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            set[i] = new HashSet<>();
        }
        return set;
    }

    void getRoadList(int[][] roads) {
        roadList = new ArrayList[nodeNum + 1];
        for (int i = 1; i <= nodeNum; i++) {
            roadList[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            roadList[road[0]].add(road[1]);
        }
    }

    void getRootNodes() {
        for (int i = 1; i <= nodeNum; i++) {
            if (i == unionFind.find(i)) {
                rootNodes.add(i);
            }
        }
    }

    void unionCycleProcess() {
        pathStack.push(1);
        Arrays.fill(stackIdx, -1);
        stackIdx[1] = 0;
        visited[1] = true;
        unionCycle(1);
    }

    void unionCycle(int node) {
        for (Integer nextNode : roadList[node]) {
            int rootNode = unionFind.find(nextNode);

            if (!visited[rootNode]) {
                visited[rootNode] = true;

                pathStack.push(rootNode);
                stackIdx[rootNode] = pathStack.size() - 1;
                unionCycle(rootNode);
                pathStack.pop();
                stackIdx[rootNode] = -1;

            } else {
                int rootIdx = stackIdx[rootNode];
                if (rootIdx != -1) {
                    unionFind.rank[rootNode] = INF;
                    for (int idx = rootIdx + 1; idx < pathStack.size(); idx++) {
                        unionFind.union(rootNode, pathStack.get(idx));
                    }
                }
            }
        }
    }

    void getReachableNodeSet(int[][] roads) {
        reachableNodeSet = getHashSet(nodeNum + 1);
        for (int[] road : roads) {
            int from = unionFind.find(road[0]);
            int to = unionFind.find(road[1]);
            if (from != to) {
                reachableNodeSet[from].add(to);
            }
        }
        visited = new boolean[nodeNum + 1];
        visited[1] = true;
        findReachableNodes(reachableNodeSet, 1);
    }

    HashSet<Integer> findReachableNodes(HashSet<Integer>[] roadSet, int node) {
        ArrayList<Integer> nextNodes = new ArrayList<>();
        for (Integer next : roadSet[node]) {
            nextNodes.add(next);
        }

        for (Integer next : nextNodes) {
            if (!visited[next]) {
                visited[next] = true;
                roadSet[node].addAll(findReachableNodes(roadSet, next));
            } else {
                roadSet[node].addAll(roadSet[next]);
            }
        }
        return roadSet[node];
    }

    int matchingProcess() {
        int match = 0;
        int[] pairNext = new int[nodeNum + 1];
        int[] pairBefore = new int[nodeNum + 1];
        for (Integer node : rootNodes) {
            boolean[] visited = new boolean[nodeNum + 1];
            if (getMatching(pairNext, pairBefore, visited, node)) {
                match++;
            }
        }
        return match;
    }

    boolean getMatching(int[] pairNext, int[] pairBefore, boolean[] visited, int node) {
        for (Integer nextNode : reachableNodeSet[node]) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;

                if (pairBefore[nextNode] == 0 || getMatching(pairNext, pairBefore, visited, pairBefore[nextNode])) {
                    pairNext[node] = nextNode;
                    pairBefore[nextNode] = node;
                    return true;
                }
            }
        }
        return false;
    }

    static class UnionFind {
        int[] root, rank;

        public UnionFind(int n) {
            this.root = new int[n + 1];
            this.rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                root[i] = i;
            }
            rank[1] = INF;
        }

        public int find(int p) {
            if (root[p] != p) {
                root[p] = find(root[p]);
            }
            return root[p];
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot != qRoot) {
                if (rank[pRoot] > rank[qRoot]) {
                    root[qRoot] = pRoot;
                } else if (rank[pRoot] < rank[qRoot]) {
                    root[pRoot] = qRoot;
                } else {
                    root[qRoot] = pRoot;
                    rank[pRoot]++;
                }
            }
        }
    }
}