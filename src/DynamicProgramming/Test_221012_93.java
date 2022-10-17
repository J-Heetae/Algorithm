package DynamicProgramming;

import java.util.*;

//동전교환(냅색 알고리즘)
public class Test_221012_93 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int coinType = sc.nextInt();
        int[] coins = new int[coinType];
        for(int i=0; i<coinType; i++) coins[i] = sc.nextInt();

        int change = sc.nextInt();
        int[] dy = new int[change+1];
        for(int i=1; i<change+1; i++) dy[i] = Integer.MAX_VALUE;

        for(int i=0; i<coinType; i++) {
            for(int j=coins[i]; j<=change; j++) {
                dy[j] = Math.min(dy[j], dy[j-coins[i]]+1);
            }
        }

        System.out.print(dy[change]);
    }
}