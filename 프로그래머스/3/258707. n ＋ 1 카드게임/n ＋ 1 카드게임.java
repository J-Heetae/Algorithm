import java.util.HashSet;
import java.util.Set;

class Solution {
    Set<Integer> pick = new HashSet<>();
    Set<Integer> spare = new HashSet<>();

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int idx = 0;
        int pair = 0;

        for (; idx < n / 3; idx++) {
            if(pick.contains(n + 1 - cards[idx]))
                pair++;
            pick.add(cards[idx]);
        }

        int round = 1;
        for (; idx < n; idx += 2) {
            int first = cards[idx];
            int second = cards[idx + 1];

            if (pick.contains(n + 1 - first) && coin >= 1) {
                coin--;
                pair++;
            } else {
                spare.add(first);
            }

            if (pick.contains(n + 1 - second) && coin >= 1) {
                coin--;
                pair++;
            } else {
                spare.add(second);
            }

            if (pair < 1 && coin > 1) {
                for (Integer card : spare) {
                    if (spare.contains(n + 1 - card)) {
                        coin -= 2;
                        pair++;
                        spare.remove(card);
                        break;
                    }
                }
            }
            if (pair < 1)
                break;
            pair--;
            round++;
        }
        return round;
    }
}