import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(6, new int[][] {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[] {1, 3}, new int[] {5});
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];

        ArrayList<Route>[] routeList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            routeList[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            routeList[path[0]].add(new Route(path[1], path[2]));
            routeList[path[1]].add(new Route(path[0], path[2]));
        }

        HashSet<Integer> startSet = new HashSet<>();
        for (int gate : gates) {
            startSet.add(gate);
        }

        HashSet<Integer> topSet = new HashSet<>();
        for (int summit : summits) {
            topSet.add(summit);
        }

        int[] maxTimeArr = new int[n + 1];
        Arrays.fill(maxTimeArr, Integer.MAX_VALUE);

        PriorityQueue<Course> pq = new PriorityQueue<>();
        for (Integer top : topSet) {
            maxTimeArr[top] = -1;
            for (Route route : routeList[top]) {
                if (maxTimeArr[route.dest] >= route.time) {
                    maxTimeArr[route.dest] = route.time;
                    pq.add(new Course(top, route.dest, route.time));
                }
            }
        }

        while (!pq.isEmpty()) {
            Course course = pq.poll();

            if (startSet.contains(course.cur)) {
                answer[0] = course.top;
                answer[1] = course.maxTime;
                break;
            }

            for (Route route : routeList[course.cur]) {
                int max = Math.max(course.maxTime, route.time);
                if (maxTimeArr[route.dest] >= max) {
                    maxTimeArr[route.dest] = max;
                    pq.add(new Course(course.top, route.dest, max));
                }
            }
            maxTimeArr[course.cur] = -1;
        }
        return answer;
    }

    class Route {
        int dest, time;

        public Route(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }

    class Course implements Comparable<Course> {
        int top, cur, maxTime;

        public Course(int top, int cur, int maxTime) {
            this.top = top;
            this.cur = cur;
            this.maxTime = maxTime;
        }

        @Override
        public int compareTo(Course o) {
            if (this.maxTime == o.maxTime) {
                return this.top - o.top;
            }
            return this.maxTime - o.maxTime;
        }
    }
}