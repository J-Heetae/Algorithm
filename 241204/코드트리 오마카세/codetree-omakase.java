import java.util.*;

public class Main {

    static class Query implements Comparable<Query> {
        int order;
        int time;
        int x;
        String name;
        
        Query(int order, int time, int x, String name) {
            this.order = order;
            this.time = time;
            this.x = x;
            this.name = name;
        }

        @Override
        public int compareTo(Query o) {
            if(this.time == o.time) {
                return this.order - o.order;
            }
            return this.time - o.time;
        }
    }

    static int L;

    static List<Query> q_list = new ArrayList<>();
    static HashMap<String, List<Query>> p_q_map = new HashMap<>();
    static HashMap<String, Integer> p_in_map = new HashMap<>();
    static HashMap<String, Integer> p_x_map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        int Q = sc.nextInt();
        while(Q-- > 0) {
            int order = sc.nextInt();
            int time = sc.nextInt();

            if(order == 100) {
                int x = sc.nextInt();
                String name = sc.next();
                Query query = new Query(order, time, x, name);
                q_list.add(query);
                p_q_map.computeIfAbsent(name, k -> new ArrayList<>()).add(query);
            }

            if(order == 200) {
                int x = sc.nextInt();
                String name = sc.next();
                int n = sc.nextInt();
                Query query = new Query(order, time, x, name);
                q_list.add(query);
                p_in_map.put(name, time);
                p_x_map.put(name, x);
            }

            if(order == 300) {
                q_list.add(new Query(order, time, -1, ""));
            }
        }

        for(String name : p_q_map.keySet()) {
            List<Query> list = p_q_map.get(name);

            int customer_x = p_x_map.get(name);
            int in_time = p_in_map.get(name);
            int end_time = 0;
            
            for(Query q : list) {
                int sushi_end_time = q.time;
                int sushi_x = q.x;

                if(q.time < in_time) { //스시가 먼저
                    sushi_end_time = in_time; 
                    sushi_x = (q.x + (in_time - q.time)) % L;

                } else { //손님이 먼저
                    sushi_end_time = q.time;
                }

                sushi_end_time += (customer_x - sushi_x + L) % L;
                end_time = Math.max(end_time, sushi_end_time);
                q_list.add(new Query(111, sushi_end_time, -1, ""));
            }
            q_list.add(new Query(222, end_time, -1, ""));
        }

        Collections.sort(q_list);
        
        int sushi_count = 0;
        int customer_count = 0;
        
        for(Query q : q_list) {
            if(q.order == 100) {
                sushi_count++;
            } else if (q.order == 111) {
                sushi_count--;
            } else if (q.order == 200) {
                customer_count++;
            } else if (q.order == 222) {
                customer_count--;
            } else {
                System.out.println(customer_count + " " + sushi_count);
            }
        }
    }
}