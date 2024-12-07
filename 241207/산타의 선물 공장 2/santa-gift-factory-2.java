import java.util.*;
import java.io.*;

public class Main {

    static class Present {
        int num;
        int front;
        int back;

        Present(int num) {
            this.num = num;
            this.front = -1;
            this.back = -1;
        }
    }

    static int n, m;

    static ArrayDeque<Present>[] bArr;
    static Present[] pArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if(query == 100) {
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());

                bArr = new ArrayDeque[n + 1];
                for(int i=1; i<=n; i++) {
                    bArr[i] = new ArrayDeque<>();
                }
                
                pArr = new Present[m + 1];
                for(int pNum=1; pNum<=m; pNum++) {
                    pArr[pNum] = new Present(pNum);

                    int bNum = Integer.parseInt(st.nextToken());
                    if(!bArr[bNum].isEmpty()) {
                        bArr[bNum].peekLast().back = pNum;
                        pArr[pNum].front = bArr[bNum].peekLast().num;
                    }
                    bArr[bNum].addLast(pArr[pNum]);
                }
            }

            if(query == 200) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(moveAll(a, b)).append("\n");
            }

            if(query == 300) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(moveFront(a, b)).append("\n");
            }

            if(query == 400) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(devide(a, b)).append("\n");
            }

            if(query == 500) {
                int pNum = Integer.parseInt(st.nextToken());
                sb.append(getPresentInfo(pNum)).append("\n");
            }

            if(query == 600) {
                int bNum = Integer.parseInt(st.nextToken());
                sb.append(getBeltInfo(bNum)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int getBeltInfo(int bNum) {
        ArrayDeque<Present> belt = bArr[bNum];
        int frontNum = -1;
        int backNum = -1;
        
        if(!belt.isEmpty()) {
            frontNum = belt.peekFirst().num;
            backNum = belt.peekLast().num;
        }
        return frontNum + 2 * backNum + 3 * belt.size();
    }

    static int getPresentInfo(int pNum) {
        Present curr = pArr[pNum];
        return curr.front + 2 * curr.back;
    }

    static int devide(int a, int b) {
        ArrayDeque<Present> from = bArr[a];
        ArrayDeque<Present> to = bArr[b];

        int n = from.size() / 2;
        for(int i=0; i<n; i++) {
            customAddFirst(to, customPollFirst(from));
        }
        return to.size();
    }

    static int moveFront(int a, int b) {
        ArrayDeque<Present> from = bArr[a];
        ArrayDeque<Present> to = bArr[b];

        Present aPresent = customPollFirst(from);
        Present bPresent = customPollFirst(to);

        if(aPresent != null) {
            customAddFirst(to, aPresent);
        }

        if(bPresent != null) {
            customAddFirst(from, bPresent);
        }
        return to.size();
    }

    static void customAddFirst(ArrayDeque<Present> dq, Present present) {
        if(!dq.isEmpty()) {
            dq.peekFirst().front = present.num;
            present.back = dq.peekFirst().num;
        }
        dq.addFirst(present);
    }

    static Present customPollFirst(ArrayDeque<Present> dq) {
        Present present = null;
        if(!dq.isEmpty()) {
            present = dq.pollFirst();
            present.front = -1;
            present.back = -1;

            if(!dq.isEmpty()) {
                dq.peekFirst().front = -1;
            }    
        }
        return present;
    }

    static int moveAll(int a, int b) {
        ArrayDeque<Present> from = bArr[a];
        ArrayDeque<Present> to = bArr[b];

        while(!from.isEmpty()) {
            Present poll = from.pollLast();
            
            if(!to.isEmpty()) {
                to.peekFirst().front = poll.num;
                poll.back = to.peekFirst().num;
            }
            to.addFirst(poll);
        }
        return to.size();
    }
}