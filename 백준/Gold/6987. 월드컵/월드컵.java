import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int WIN = 0, DRAW = 1, LOSE = 2, NUM_OF_TEAM = 6;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] matches, matchResult;
    static boolean possible;

    public static void main(String[] args) throws IOException {
        initMatches();

        int testcases = 4;
        while (testcases-- > 0) {
            int numOfMatches = initMatchResult();

            possible = false;
            if (numOfMatches == 30) {
                verifyMatchResult(0);
            }

            int result = (possible) ? 1 : 0;
            System.out.print(result + " ");
        }
    }

    private static void verifyMatchResult(int matchIdx) {
        if (possible)
            return;

        if (matchIdx == matches.length) {
            possible = true;
            return;
        }

        int teamA = matches[matchIdx][0], teamB = matches[matchIdx][1];

        processMatchResult(matchIdx, teamA, teamB, WIN, LOSE);
        processMatchResult(matchIdx, teamA, teamB, DRAW, DRAW);
        processMatchResult(matchIdx, teamA, teamB, LOSE, WIN);
    }

    private static void processMatchResult(int matchIdx, int teamA, int teamB, int resultA, int resultB) {
        if (matchResult[teamA][resultA] > 0 && matchResult[teamB][resultB] > 0) {
            matchResult[teamA][resultA]--;
            matchResult[teamB][resultB]--;
            verifyMatchResult(matchIdx + 1);
            matchResult[teamA][resultA]++;
            matchResult[teamB][resultB]++;
        }
    }

    private static int initMatchResult() throws IOException {
        matchResult = new int[6][3];
        st = new StringTokenizer(br.readLine());
        int numOfMatches = 0;
        for (int team = 0; team < NUM_OF_TEAM; team++) {
            matchResult[team][WIN] = Integer.parseInt(st.nextToken());
            matchResult[team][DRAW] = Integer.parseInt(st.nextToken());
            matchResult[team][LOSE] = Integer.parseInt(st.nextToken());
            numOfMatches += matchResult[team][WIN];
            numOfMatches += matchResult[team][DRAW];
            numOfMatches += matchResult[team][LOSE];
        }
        return numOfMatches;
    }

    private static void initMatches() {
        int numOfMatches = 0;
        for (int i = 0; i < NUM_OF_TEAM; i++) {
            numOfMatches += i;
        }

        matches = new int[numOfMatches][2];

        int idx = 0;
        for (int teamA = 0; teamA < NUM_OF_TEAM - 1; teamA++) {
            for (int teamB = teamA + 1; teamB < NUM_OF_TEAM; teamB++) {
                matches[idx][0] = teamA;
                matches[idx][1] = teamB;
                idx++;
            }
        }
    }
}