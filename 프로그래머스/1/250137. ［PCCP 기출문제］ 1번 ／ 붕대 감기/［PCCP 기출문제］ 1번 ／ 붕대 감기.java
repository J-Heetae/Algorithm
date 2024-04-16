class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0;
        int curHealth = health;

        int timeForAddRecovery = bandage[0];
        int recoveryPerSecond = bandage[1];
        int additionalRecovery = bandage[2];

        for (int[] attack : attacks) {
            int timePass = attack[0] - 1 - time;
            curHealth = Math.min(health,
                curHealth + (timePass / timeForAddRecovery * additionalRecovery) + (timePass * recoveryPerSecond));

            curHealth -= attack[1];

            if (curHealth <= 0)
                break;

            time = attack[0];
        }

        return (curHealth <= 0) ? -1 : curHealth;
    }

}