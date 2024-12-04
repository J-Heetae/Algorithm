import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer> sushiXList;
    static ArrayList<Integer> customerXList;
    static HashMap<Integer, HashMap<String, Integer>> sushiMap;
    static HashMap<Integer, HashMap<String, Integer>> customerMap;

    static int L;
    static int time, timeBefore;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        sushiXList = new ArrayList<>();
        customerXList = new ArrayList<>();
        sushiMap = new HashMap<>();
        customerMap = new HashMap<>();
        
        timeBefore = 1;
        
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
        
            //초밥 회전
            sushiSpin();
        
            if(order == 100) { //초밥 놓기
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                putSushi(x, name);
                
            }

            if(order == 200) { //손님 착석
                int x = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                customerIn(x, name, n);
            }

            if(order == 300) { //사진 찍기
                int customerCount = 0;
                int sushiCount = 0;

                for(int x : customerMap.keySet()) {
                    customerCount += customerMap.get(x).size();
                }

                for(int x : sushiMap.keySet()) {
                    HashMap<String, Integer> currSushiMap = sushiMap.get(x);
                    for(String name : currSushiMap.keySet()) {
                        sushiCount += currSushiMap.get(name);
                    }
                }

                System.out.println(customerCount + " " + sushiCount);
            }
            timeBefore = time;
        }
    }

    static void putSushi(int x, String name) {
        int sushiX = (L + x - (time % L)) % L;
        if(!sushiMap.containsKey(sushiX)) {
            sushiMap.put(sushiX, new HashMap<>());
            sushiXList.add(sushiX);
        }
        sushiMap.get(sushiX).put(name, sushiMap.get(sushiX).getOrDefault(name, 0) + 1);
        eat(x, name);
    }

    static void customerIn(int x, String name, int n) {
        if(!customerMap.containsKey(x)) {
            customerMap.put(x, new HashMap<>());
            customerXList.add(x);
        }
        customerMap.get(x).put(name, customerMap.get(x).getOrDefault(name, 0) + n);
        eat(x, name);
    }

    static void eat(int x, String name) {
        int sushiX = (L + x - (time % L)) % L;
        
        if(sushiMap.containsKey(sushiX) && customerMap.containsKey(x)) {
            HashMap<String, Integer> currSushiMap = sushiMap.get(sushiX);
            HashMap<String, Integer> currCusMap = customerMap.get(x);
            
            if(currSushiMap.containsKey(name) && currCusMap.containsKey(name)) {
                int made = currSushiMap.get(name);
                int need = currCusMap.get(name);
                
                if(made == need) {
                    currSushiMap.remove(name);
                    currCusMap.remove(name);
                } else if (made > need) {
                    currSushiMap.put(name, (made - need));
                    currCusMap.remove(name);
                } else if (made < need) {
                    currSushiMap.remove(name);
                    currCusMap.put(name, (need - made));
                }
            }

            if(currSushiMap.size() == 0) {
                sushiMap.remove(sushiX);
                sushiXList.remove(Integer.valueOf(sushiX));
            }

            if(currCusMap.size() == 0) {
                customerMap.remove(x);
                customerXList.remove(Integer.valueOf(x));
            }
        }
    }

    static void sushiSpin() {
        int range = (time - timeBefore >= (L - 1)) ? (L - 1) : time - timeBefore;

        if(range == 0) {
            return;
        }
        
        Collections.sort(sushiXList);
        Collections.sort(customerXList);

        ArrayList<Integer> removeSushiX = new ArrayList<>();
        ArrayList<Integer> removeCusX = new ArrayList<>();

        //음식기준으로 돌아야함
        for(int sushiX : sushiXList) {
            int fixedSushiX = (sushiX + (timeBefore % L )) % L;
            int fromX = fixedSushiX + 1;
            int toX = (fixedSushiX + range) % L;

            HashMap<String, Integer> currSushiMap = sushiMap.get(sushiX);
            
            for(int customerX : customerXList) {
                if(toX > fromX) {
                    if(customerX < fromX) {
                        continue;
                    } else if(customerX > toX) {
                        break;
                    }
                } else if (fromX > toX) {
                    if(toX < customerX && customerX < fromX) {
                        continue;
                    }
                } else {
                    if(customerX > toX) {
                        break;
                    } else if (customerX != fromX) {
                        continue;
                    }
                }

                //범위에 포함됨
                HashMap<String, Integer> currCusMap = customerMap.get(customerX);

                ArrayList<String> removeSushi = new ArrayList<>();

                for(String name : currSushiMap.keySet()) {
                    if(!currCusMap.containsKey(name)) {
                        continue;
                    }
                    int made = currSushiMap.get(name);
                    int need = currCusMap.get(name);

                    if(made == need) {
                        removeSushi.add(name);
                        currCusMap.remove(name);
                    } else if (made > need) {
                        currSushiMap.put(name, (made - need));
                        currCusMap.remove(name);
                    } else if (made < need) {
                        removeSushi.add(name);
                        currCusMap.put(name, (need - made));
                    }
                }

                if(currCusMap.size() == 0) {
                    removeCusX.add(customerX);
                }

                for(String name : removeSushi) {
                    currSushiMap.remove(name);
                }
            }

            if(currSushiMap.size() == 0) {
                removeSushiX.add(sushiX);
            }
        }

        for(Integer x : removeSushiX) {
            sushiMap.remove(x);
            sushiXList.remove(x);
        }
        
        for(Integer x : removeCusX) {
            customerMap.remove(x);
            customerXList.remove(x);
        }
    }
}