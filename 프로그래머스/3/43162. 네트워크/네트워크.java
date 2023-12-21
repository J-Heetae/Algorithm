class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        int solution = s.solution(5,
            new int[][] {{1, 0, 0, 0, 1}, {0, 1, 1, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 1, 1}, {1, 0, 0, 1, 1}});

        System.out.println(solution);
    }

    static int[] networks;
    public int solution(int n, int[][] computers) {
        networks = new int[n];
        for(int i=0; i<n; i++) {
            networks[i] = i;
        }

        for(int cur=0; cur<n; cur++) {
            for(int next=0; next<n; next++) {
                if(cur != next && computers[cur][next] == 1) {
                    connect(cur, next);
                }
            }
        }

        int[] count = new int[n];
        for(int idx=0; idx<networks.length; idx++) {
            count[getNetwork(idx)]++;
        }

        int answer = 0;
        for(int c : count) {
            if(c > 0) {
                answer++;
            }
        }
        return answer;
    }

    public void connect(int a, int b) {
        int networkA = getNetwork(a);
        int networkB = getNetwork(b);

        if(networkA != networkB) {
            if(networkA < networkB) {
                networks[networkB] = networkA;
            } else {
                networks[networkA] = networkB;
            }
        }
    }

    public int getNetwork(int a) {
        if(networks[a] == a) {
            return a;
        }
        return networks[a] = getNetwork(networks[a]);
    }
}