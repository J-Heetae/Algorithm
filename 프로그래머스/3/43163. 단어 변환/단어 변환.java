import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] isVisited = new boolean[words.length];

        Queue<Word> que = new LinkedList<>();
        que.offer(new Word(begin, 0));

        while(!que.isEmpty()) {
            Word cur = que.poll();

            if(cur.value.equals(target)) {
                return cur.changeCnt;
            }

            for(int i=0; i<words.length; i++) {
                if(isVisited[i] || !canChange(cur.value, words[i]))
                    continue;

                isVisited[i] = true;
                que.offer(new Word(words[i], cur.changeCnt + 1));
            }
        }
        return 0;
    }

    boolean canChange(String a, String b) {
        int cnt = 0;
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) != b.charAt(i))
                cnt++;
        }
        return cnt == 1;
    }


    class Word {
        String value;
        int changeCnt;

        public Word(String value, int changeCnt) {
            this.value = value;
            this.changeCnt = changeCnt;
        }
    }
}