package GreedyAlgorithm;


import java.util.Scanner;

//씨름선수
public class Test_220923_82 {

    private static class Wrestler {
        int height;
        int weight;

        public Wrestler(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int drop = 0;
        Wrestler[] players = new Wrestler[num];
        int[] ch = new int[num];

        for(int i=0; i<num; i++) {
            int height = sc.nextInt();
            int weight = sc.nextInt();
            players[i] = new Wrestler(height, weight);
        }

        int minHeight = Integer.MAX_VALUE, minWeight = Integer.MAX_VALUE;

        for (int i=0; i<num; i++) {
            Wrestler player1 = players[i];
            if(ch[i] == 0) {

                for (int j=0; j<num; j++) {
                    Wrestler player2 = players[j];
                    if(ch[j] == 0) {
                        if(drop > 0) {
                            if (player1.weight < minWeight && player1.height < minHeight) {
                                minWeight = player1.weight;
                                minHeight = player1.height;
                                drop++;
                                ch[i]--;
                                break;
                            }
                        }

                        if (player1.weight < players[j].weight && player1.height < player2.height) {
                            drop++;
                            ch[i]--;
                            break;
                        } else if (player1.weight > player2.weight)
                            minWeight = Math.min(minWeight, player2.weight);

                        else if (player1.height > player2.height)
                            minHeight = Math.min(minHeight, player2.height);
                    }
                }
            }
        }

        int select = num - drop;
        System.out.println(select);

    }
}