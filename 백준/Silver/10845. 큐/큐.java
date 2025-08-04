import java.io.*;
import java.util.*;

public class Main {

    static class CustomNode {
        int value;
        CustomNode prev;
        CustomNode next;

        CustomNode(int value) {
            this.value = value;
        }
    }

    static class CustomQueue {
        private int size;
        private CustomNode first;
        private CustomNode last;

        void push(int value) {
            CustomNode newNode = new CustomNode(value);
            size++;
            if (size == 1) {
                first = newNode;
            } else {
                CustomNode currNode = first;
                while (currNode.next != null) {
                    currNode = currNode.next;
                }
                currNode.next = newNode;
                newNode.prev = currNode;
            }
            last = newNode;
        }

        int pop() {
            if (size == 0) {
                return -1;
            }
            size--;

            CustomNode pop = first;

            if (size != 0) {
                first = first.next;
                first.prev = null;
            } else {
                first = null;
                last = null;
            }

            return pop.value;
        }

        int size() {
            return size;
        }

        int empty() {
            if(size == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int front() {
            if(empty() == 1) {
                return -1;
            }
            return first.value;
        }

        int back() {
            if(empty() == 1) {
                return -1;
            }
            return last.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        CustomQueue customQueue = new CustomQueue();
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            if ("push".equals(command)) {
                int number = Integer.parseInt(st.nextToken());
                customQueue.push(number);
            } else if ("pop".equals(command)) {
                sb.append(customQueue.pop()).append("\n");
            } else if ("size".equals(command)) {
                sb.append(customQueue.size()).append("\n");
            } else if ("empty".equals(command)) {
                sb.append(customQueue.empty()).append("\n");
            } else if ("front".equals(command)) {
                sb.append(customQueue.front()).append("\n");
            } else if ("back".equals(command)) {
                sb.append(customQueue.back()).append("\n");
            }
        }
        System.out.println(sb);
    }
}