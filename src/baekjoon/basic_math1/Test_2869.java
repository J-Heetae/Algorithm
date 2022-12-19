package baekjoon.basic_math1;


import java.io.*;
import java.util.StringTokenizer;

public class Test_2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int up = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());
        int height= Integer.parseInt(st.nextToken());

        int day = (height - down) / (up - down);
        if((height - down) % (up - down) != 0) day++;

        System.out.println(day);
    }
}
