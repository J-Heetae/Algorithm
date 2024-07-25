import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(reader.readLine());

        ArrayList<Integer> ropes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ropes.add(Integer.parseInt(reader.readLine()));
        }
        ropes.sort(Collections.reverseOrder());

        int maxWeight = ropes.get(0);
        for (int i = 1; i < ropes.size(); i++) {
            if(ropes.get(i) * (i + 1) > maxWeight) {
                maxWeight = ropes.get(i) * (i + 1);
            }
        }
        System.out.println(maxWeight);
    }
}