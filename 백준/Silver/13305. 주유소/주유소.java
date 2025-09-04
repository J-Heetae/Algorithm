import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] distance = new long[n-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n-1; i++)
            distance[i] = Long.parseLong(st.nextToken());

        long[] prices = new long[n-1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n-1; i++)
            prices[i] = Long.parseLong(st.nextToken());

        long minPrice = prices[0];
        long result = prices[0] * distance[0];
        for(int i=1; i<n-1; i++) {
            if(prices[i] <= minPrice) {
                minPrice = prices[i];
            }
            result += minPrice * distance[i];
        }
        System.out.print(result);
    }
}