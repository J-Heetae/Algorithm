import java.util.HashMap;

class Solution {

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

        if(curMember.parentIdx == -1) {
            curMember.profit += profit;
            return;
        }

        int payment = profit / 10;

        if(payment == 0)
            return;

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