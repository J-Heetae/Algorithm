import java.util.*;

class Solution {

    int maxService = 0;
    int maxPrice = 0;
    int[] saleArr;
    int[] emoArr;
    int[][] userArr;
    int[] maxSale;

    public int[] solution(int[][] users, int[] emoticons) {
        this.saleArr = new int[emoticons.length];
        this.emoArr = emoticons;
        this.userArr = users;
        int min = 40;
        int max = 0;
        for(int i=0; i<userArr.length; i++) {
            max = Math.max(max, userArr[i][0]);
            min = Math.min(min, userArr[i][0]);
        }
        combination(0, (int)(Math.ceil((double)min / 10) * 10), (int)(Math.ceil((double)max / 10) * 10));
        
        System.out.println(Arrays.toString(maxSale));
        return new int[]{maxService, maxPrice};
    }

    void combination(int idx, int min, int max) {
        if(idx == saleArr.length) {
            calculate();
            return;
        }

        for(int i = min; i <= max; i += 10) {
            saleArr[idx] = i;
            combination(idx + 1, min, max);
        }
    }

    void calculate() {
        int[] salePriceArr = new int[emoArr.length];
        for(int i=0; i<salePriceArr.length; i++) {
            salePriceArr[i] = emoArr[i] / 100 * (100 - saleArr[i]);
        }

        int sumService = 0;
        int sumPrice = 0;

        user:
        for(int i=0; i<userArr.length; i++) {
            int[] currUser = userArr[i];
            int price = 0;
            for(int j=0; j<emoArr.length; j++) {
                if(saleArr[j] >= currUser[0]) {
                    price += salePriceArr[j];
                }
                if(price >= currUser[1]) {
                    sumService++;
                    continue user;
                }
            }
            sumPrice += price;
        }
        if (sumService > maxService) {
            maxService = sumService;
            maxPrice = sumPrice;
        } else if (sumService == maxService && sumPrice > maxPrice) {
            maxPrice = sumPrice;
        }
    }
}