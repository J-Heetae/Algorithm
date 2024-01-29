class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});

    }

    static int[] status;
    static int[][] connected;
    static int networkNumber = 1;
    static int computerNumber;

    public int solution(int n, int[][] computers) {
        computerNumber = n;
        status = new int[n];
        connected = computers;

        for(int i=0; i<n; i++) {
            if(status[i] == 0) {
                connect(i, networkNumber++);
            }
        }

        return networkNumber - 1;
    }

    void connect(int cur, int network) {
        for(int next=0; next<computerNumber; next++) {
            if(connected[cur][next] == 1 && status[next] == 0) {
                status[next] = network;
                connect(next, network);
            }
        }
    }
}