import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    static ArrayList<Integer>[] people;
    static boolean[] trueParty;
    static ArrayList<Integer>[] partyPeopleList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfPeople = Integer.parseInt(st.nextToken());
        int numOfParty = Integer.parseInt(st.nextToken());

        trueParty = new boolean[numOfParty];
        people = new ArrayList[numOfPeople + 1];
        for(int i=1; i<=numOfPeople; i++) {
            people[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int numOfTruePeople = Integer.parseInt(st.nextToken());
        int[] truePeoples = new int[numOfTruePeople];
        for(int i=0; i<numOfTruePeople; i++) {
            int cur = Integer.parseInt(st.nextToken());
            truePeoples[i] = cur;
        }

        if(numOfTruePeople == 0) {
            System.out.println(numOfParty);
            System.exit(0);
        }

        partyPeopleList = new ArrayList[numOfParty];
        for(int i=0; i<numOfParty; i++) {
            partyPeopleList[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int curPeopleNum = Integer.parseInt(st.nextToken());
            for(int j=0; j<curPeopleNum; j++) {
                int curPeople = Integer.parseInt(st.nextToken());
                partyPeopleList[i].add(curPeople);
                people[curPeople].add(i);
            }
        }

        for(int t : truePeoples) {
            check(t);
        }

        int cnt = 0;
        for(boolean p : trueParty) {
            if(!p) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void check(int num) {
        for(int party : people[num]) {
            if(trueParty[party]) continue;
            trueParty[party] = true;
            for(int next : partyPeopleList[party]) {
                check(next);
            }
        }
    }
}