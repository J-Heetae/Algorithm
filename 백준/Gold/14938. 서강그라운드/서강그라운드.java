import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());

        int regionNumber = Integer.parseInt(tokenizer.nextToken()); //지역 개수
        int searchRange = Integer.parseInt(tokenizer.nextToken()); //수색 범위
        int roadNumber = Integer.parseInt(tokenizer.nextToken()); //길의 개수

        int[] itemNumberArr = new int[regionNumber + 1];
        int[][] roadArr = new int[regionNumber + 1][regionNumber + 1];

        //지역별 아이템 개수 입력받기
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= regionNumber; i++) {
            itemNumberArr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        //길 입력 받기
        for (int i = 0; i < roadNumber; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int regionA = Integer.parseInt(tokenizer.nextToken());
            int regionB = Integer.parseInt(tokenizer.nextToken());
            int distance = Integer.parseInt(tokenizer.nextToken());
            roadArr[regionA][regionB] = distance;
            roadArr[regionB][regionA] = distance;
        }

        int maxItemCount = 0;
        for (int startRegion = 1; startRegion <= regionNumber; startRegion++) {
            int obtainedItemCount = 0;
            int[] minDistanceArr = new int[regionNumber + 1];

            Arrays.fill(minDistanceArr, Integer.MAX_VALUE);
            minDistanceArr[startRegion] = 0;

            PriorityQueue<int[]> roadQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            for (int nextRegion = 1; nextRegion <= regionNumber; nextRegion++) {
                if (roadArr[startRegion][nextRegion] > 0) {
                    roadQueue.offer(new int[] {nextRegion, roadArr[startRegion][nextRegion]});
                }
            }

            while (!roadQueue.isEmpty()) {
                int[] poll = roadQueue.poll();

                int region = poll[0];
                int distance = poll[1];

                if (distance <= searchRange && minDistanceArr[region] > distance) {
                    minDistanceArr[region] = distance;

                    for (int nextRegion = 1; nextRegion <= regionNumber; nextRegion++) {
                        if (roadArr[region][nextRegion] != 0 //길이 있고
                            && roadArr[region][nextRegion] + distance <= searchRange //수색범위 안이고
                            && minDistanceArr[nextRegion] > roadArr[region][nextRegion] + distance) { //최소 거리이면
                            roadQueue.offer(new int[] {nextRegion, roadArr[region][nextRegion] + distance});
                        }
                    }
                }
            }

            for (int region = 1; region <= regionNumber; region++) {
                if (minDistanceArr[region] <= searchRange) {
                    obtainedItemCount += itemNumberArr[region];
                }
            }

            maxItemCount = Math.max(maxItemCount, obtainedItemCount);
        }
        System.out.println(maxItemCount);
    }

}