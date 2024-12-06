import java.util.*;

public class Main {

    static class Task implements Comparable<Task> {
        int p;
        int in_time;
        String domain;
        String url;

        Task(int p, int in_time, String url) {
            this.p = p;
            this.in_time = in_time;
            this.domain = url.split("/")[0];
            this.url = url;
        }

        @Override
        public int compareTo(Task o) {
            if(this.p == o.p) {
                return this.in_time - o.in_time;
            }
            return this.p - o.p;
        }
    }

    static class WaitingTask implements Comparable<WaitingTask> {
        int time;
        Task task;
        
         WaitingTask(int time, Task task) {
            this.time = time;
            this.task = task;
         }

         @Override
        public int compareTo(WaitingTask o) {
            return this.time - o.time;
        }
    }

    static class Judging {
        int start_time;
        String domain;

        Judging(int start_time, String domain) {
            this.start_time = start_time;
            this.domain = domain;
        }
    }

    static int N, t;
    static PriorityQueue<Integer> judger_que = new PriorityQueue<>();
    
    static HashSet<String> url_set = new HashSet<>();
    static PriorityQueue<Task> waiting_que = new PriorityQueue<>();
    static PriorityQueue<WaitingTask> sub_waiting_que = new PriorityQueue<>();

    static HashMap<String, ArrayList<Task>> judging_domain_map = new HashMap<>();
    static Judging[] judging_arr;

    static HashMap<String, Integer> history_map = new HashMap<>();
    
    static void init() {
        t = 1;
        judging_arr = new Judging[N + 1];
        for(int i=1; i<=N; i++) {
            judger_que.offer(i);
        }
    }

    static void taskIn(int p, String url) {
        if(url_set.contains(url)) { //대기 큐에 일치하는 url이 있다면 넘어감
            return;
        }
        url_set.add(url);
        waiting_que.offer(new Task(p, t, url));
    }

    static void judgeTask() {
        while(!waiting_que.isEmpty()) {
            Task poll = waiting_que.poll();
            
            //현재 채점 진행중인 도메인
            if(judging_domain_map.containsKey(poll.domain)) {
                judging_domain_map.get(poll.domain).add(poll);
                continue;
            }
            //부적절한 채점
            if(history_map.containsKey(poll.domain) && t < history_map.get(poll.domain)) {
                sub_waiting_que.offer(new WaitingTask(history_map.get(poll.domain), poll));
                continue;
            }
            //즉시 채점 가능
            if(!judger_que.isEmpty()) { //채점 가능한 채점기 있음
                url_set.remove(poll.url);

                int judger_num = judger_que.poll();
                judging_domain_map.put(poll.domain, new ArrayList<>());
                judging_arr[judger_num] = new Judging(t, poll.domain);
                return;
            }
        }
    }

    static void judgeFinish(int judge_num) {
        if(judging_arr[judge_num] != null) { //진행하던 채점이 있다면
            Judging finish = judging_arr[judge_num];
            judging_arr[judge_num] = null;

            //현재 task의 도메인과 같아서 기다리고 있던 도메인들 다시 대기
            for(Task task : judging_domain_map.get(finish.domain)) {
                waiting_que.offer(task);
            }
            judging_domain_map.remove(finish.domain);
            
            //해당 도메인의 채점을 언제 다시 할 수 있는지
            int gap = t - finish.start_time;
            int limit_time = finish.start_time + (3 * gap);
            history_map.put(finish.domain, limit_time);

            //채점기 다시 대기
            judger_que.offer(judge_num);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();
        while(Q-- > 0) {
            int query = sc.nextInt();

            if(query == 100) {
                N = sc.nextInt();
                init();
                
                int p = 1;
                String url = sc.next();
                taskIn(p, url);

                // int sub = 0;
                // for(String domain : judging_domain_map.keySet()) {
                //     // count += judging_domain_map.get(domain).size();
                //     sub += judging_domain_map.get(domain).size();
                // }
                // System.out.println("t = " + t + ", query =  " + query + ", waiting_que = " + waiting_que.size() + ", sub_waiting_que = " + sub_waiting_que.size() + ", judging_domain_map = " + sub);
                continue;
            }

            t = sc.nextInt();
            while(!sub_waiting_que.isEmpty() && t >= sub_waiting_que.peek().time) {
                waiting_que.offer(sub_waiting_que.poll().task);
            }

            if(query == 200) {
                int p = sc.nextInt();
                String url = sc.next();
                taskIn(p, url);
            }

            if(query == 300) {
                judgeTask();
            }

            if(query == 400) {
                int judger_num = sc.nextInt();
                judgeFinish(judger_num);
            }

            int sub = 0;
            for(String domain : judging_domain_map.keySet()) {
                // count += judging_domain_map.get(domain).size();
                sub += judging_domain_map.get(domain).size();
            }

            if(query == 500) {
                int count = 0;
                count += waiting_que.size();
                count += sub_waiting_que.size();
                // int sub = 0;
                for(String domain : judging_domain_map.keySet()) {
                    count += judging_domain_map.get(domain).size();
                    // sub += judging_domain_map.get(domain).size();
                }
                System.out.println(count);
            }
            // System.out.println("t = " + t + ", query =  " + query + ", waiting_que = " + waiting_que.size() + ", sub_waiting_que = " + sub_waiting_que.size() + ", judging_domain_map = " + sub);
        }
    }
}