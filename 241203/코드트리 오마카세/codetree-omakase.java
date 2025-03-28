import java.util.*;
import java.io.*;

public class Main {

    static Map<Integer, Map<String, Integer>> sushis;
    static Map<Integer, Map<String, Integer>> customers;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        sushis = new HashMap<>();
        customers = new HashMap<>();
        
        int beforeTime = 0;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // t초의 x위치는 (L + x - (t % L))
            // 6초의 0위치는 (5 + 0 - (6 % 5)) 
            
            if(order == 100) {
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();

                x = (L + x - (t % L)) % L;
                if(!sushis.containsKey(x)) {
                    sushis.put(x, new HashMap<>());
                }
                Map<String, Integer> curr = sushis.get(x);
                curr.put(name, curr.getOrDefault(name, 0) + 1);
            
            } else if (order == 200) {
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if(!customers.containsKey(x)) {
                    customers.put(x, new HashMap<>());
                }
                Map<String, Integer> curr = customers.get(x);
                curr.put(name, curr.getOrDefault(name, 0) + n);
                
            }

            int range = ((t - beforeTime) >= (L - 1))? L - 1 : t - beforeTime;
            for(int x : customers.keySet()) {
                Map<String, Integer> customerMap = customers.get(x);

                for(int i=0; i<range; i++) {
                    x = ((L + x - (t % L)) % L + i) % L;

                    Map<String, Integer> sushiMap = sushis.get(x);

                    if(sushiMap == null) {
                        continue;
                    }

                    List<String> removeSushi = new ArrayList<>();
                    List<String> removeCustomer = new ArrayList<>();

                    for(String name : customerMap.keySet()) {
                        int need = customerMap.get(name);
                        if(!sushiMap.containsKey(name)) {
                            continue;
                        }
                        int made = sushiMap.get(name);
                        int total = need - made;
                        
                        if(total == 0) {
                            removeSushi.add(name);
                            removeCustomer.add(name);
                        } else if (total < 0) {
                            removeCustomer.add(name);
                            sushiMap.put(name, (-1 * total));
                        } else {
                            removeSushi.add(name);
                            customerMap.put(name, total);
                        }
                    }

                    for(String name : removeSushi) {
                        sushiMap.remove(name);
                    }

                    for(String name : removeCustomer) {
                        customerMap.remove(name);
                    }
                }
            }

            beforeTime = t;

            if(order == 300) {
                int customerCount = 0;
                int sushiCount = 0;

                for(int x : customers.keySet()) {
                    Map<String, Integer> customerMap = customers.get(x);
                    customerCount += customerMap.size();
                    continue;
                }

                for(int x : sushis.keySet()) {
                    Map<String, Integer> sushiMap = sushis.get(x);
                    for(String name : sushiMap.keySet()) {
                        sushiCount += sushiMap.get(name);
                    }
                }
                System.out.println(customerCount + " " + sushiCount);
            }

            // System.out.println("현재 시간 = " + t);
            // System.out.println("스시들");
            // for(int x : sushis.keySet()) {
            //     int currX = (x + (t % L)) % L;
            //     System.out.print("원래 x = " + x + ", 현재는 = " + currX + " > ");
            //     for(String name : sushis.get(x).keySet()) {
            //         System.out.print(name + " " + sushis.get(x).get(name) + ", ");
            //     }
            // }
            // System.out.println();
            // System.out.println("손님들");
            // for(int x : customers.keySet()) {
            //     System.out.print(x + " = ");
            //     for(String name : customers.get(x).keySet()) {
            //         System.out.print(name + " " + customers.get(x).get(name) + ", ");
            //     }
            // }
            // System.out.println();
            // System.out.println("=========================");
        }
    }
}