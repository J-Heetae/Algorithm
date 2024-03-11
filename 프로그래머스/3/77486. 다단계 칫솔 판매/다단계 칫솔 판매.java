import java.util.HashMap;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
            new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
            new String[] {"young", "john", "tod", "emily", "mary"},
            new int[] {12, 4, 2, 5, 10});
    }

    static HashMap<String, Integer> memberMap = new HashMap<>();
    static Member[] memberList;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int memberNumber = enroll.length;

        memberList = new Member[memberNumber];
        for(int i=0; i<memberNumber; i++) {
            memberList[i] = new Member(i);
            memberMap.put(enroll[i], i);
        }

        for(int i=0; i<memberNumber; i++) {
            if(referral[i].equals("-")) {
                memberList[i].parentIdx = -1;
            } else {
                memberList[i].parentIdx = memberMap.get(referral[i]);
            }
        }

        for(int i=0; i<seller.length; i++) {
            int curSeller = memberMap.get(seller[i]);
            int totalProfit = amount[i] * 100;
            repayment(curSeller, totalProfit);
        }

        int[] result = new int[memberNumber];
        for(int i=0; i<memberNumber; i++) {
            result[i] = memberList[i].profit;
        }

        return result;
    }

    private void repayment(int idx, int profit) {
        Member curMember = memberList[idx];

        int payment = profit / 10;

        if(payment == 0) {
            curMember.profit += profit;
            return;
        }

        if(curMember.parentIdx == -1) {
            curMember.profit += profit - payment;
            return;
        }

        curMember.profit += profit - payment;
        repayment(curMember.parentIdx, payment);
    }

    private class Member {
        int idx, parentIdx, profit;
        public Member(int idx) {
            this.idx = idx;
        }
    }
}