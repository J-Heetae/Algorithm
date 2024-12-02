import java.util.*;
import java.io.*;

public class Main {

    static class City {
        int num;
        int weight;

        City(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static class Product implements Comparable<Product> {
        int id;
        int revenue;
        int dest;
        int cost;
        
        Product(int id, int revenue, int dest, int cost) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Product o) {
            int thisProfit = ((this.revenue - this.cost) < 0) ? -1 : this.revenue - this.cost;
            int oProfit = ((o.revenue - o.cost) < 0) ? -1 : o.revenue - o.cost;

            if(thisProfit == oProfit) {
                return this.id - o.id;
            }
            return oProfit - thisProfit;
        }
    }

    static int n;
    static StringBuilder sb = new StringBuilder();
    static List<List<City>> cityList = new ArrayList<>();
    static PriorityQueue<Product> productQueue = new PriorityQueue<>();
    static int[] distance;
    static boolean[] deleted = new boolean[30_001]; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            
            if(query == 100) {
                n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                for(int i=0; i<n; i++) {
                    cityList.add(new ArrayList<>());
                }
                
                for(int i=0; i<m; i++) {
                    int v = Integer.parseInt(st.nextToken());
                    int u = Integer.parseInt(st.nextToken());
                    int weight = Integer.parseInt(st.nextToken());
                    if(u == v) {
                        continue;
                    }
                    cityList.get(v).add(new City(u, weight));
                    cityList.get(u).add(new City(v, weight));
                }

                distance = new int[n];
                dijkstra(0);

            } else if (query == 200) {
                int id = Integer.parseInt(st.nextToken());
                int revenue = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                productQueue.offer(new Product(id, revenue, dest, distance[dest]));

            } else if (query == 300) {
                int id = Integer.parseInt(st.nextToken());
                deleted[id] = true;

            } else if (query == 400) {
                if(!productQueue.isEmpty()) {
                    Product bestProduct = productQueue.peek();
                
                    while (deleted[bestProduct.id]) {
                        productQueue.poll();
                        if(!productQueue.isEmpty()) {
                            bestProduct = productQueue.peek();
                        } else {
                            bestProduct = null;
                            break;
                        }
                    }

                    if(bestProduct == null || bestProduct.cost > bestProduct.revenue) {
                        sb.append(-1);
                    } else {
                        productQueue.poll();
                        sb.append(bestProduct.id);
                    }
                } else {
                    sb.append(-1);
                }
                sb.append("\n");
            } else {
                int s = Integer.parseInt(st.nextToken());
                dijkstra(s);

                int size = productQueue.size();
                Product[] tmp = new Product[productQueue.size()];

                for(int i=0; i<size; i++) {
                    Product product = productQueue.poll();
                    product.cost = distance[product.dest];
                    tmp[i] = product;
                }

                for(int i=0; i<size; i++) {
                    productQueue.offer(tmp[i]);
                }
            }
        }
        System.out.print(sb);
    }

    static void dijkstra(int s) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{s, 0});

        while(!pq.isEmpty()) {
            int[] poll = pq.poll();
            int num = poll[0];
            int weight = poll[1];

            if(distance[num] <= weight) {
                continue;
            }
            distance[num] = weight;

            for(City next : cityList.get(num)) {
                if(distance[next.num] > next.weight + distance[num]) {
                    pq.offer(new int[]{next.num, next.weight + distance[num]});
                }
            }
        }        
    }
}