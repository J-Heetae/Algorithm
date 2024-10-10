import java.util.*;

class Solution {
    
    static Robot[] robots;
    static int[][] points, danger;
    
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        this.robots = new Robot[routes.length];
    
        for(int i=0; i<routes.length; i++) {
            int[] route = routes[i];
            robots[i] = new Robot(route);
        }
        
        int totalDanger = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            danger = new int[101][101];
            for(Robot robot : robots) {
                if(robot.move()) {
                    flag = true;
                }
            }
            for(int i=1; i<=100; i++) {
                for(int j=1; j<=100; j++) {
                    if(danger[i][j] > 1) {
                        totalDanger++;
                    }
                }
            }
        }
        return totalDanger;
    }
    
    class Robot {
        int x, y, routeIdx;
        int[] route;
        
        public Robot(int[] route) {
            this.routeIdx = -1;
            this.route = new int[route.length];
            for(int i=0; i<route.length; i++) {
                this.route[i] = route[i] - 1;
            }
        }
        
        public boolean move() {
            if(routeIdx >= route.length - 1) {
                this.x = 0;
                this.y = 0;
                return false;
            }
            
            int pointX = points[route[routeIdx + 1]][0];
            int pointY = points[route[routeIdx + 1]][1];
            
            if(routeIdx == -1) {
                this.x = pointX;
                this.y = pointY;
                
            } else {
                if(this.x != pointX) {
                    if(this.x > pointX) {
                        this.x--;
                    } else {
                        this.x++;
                    }
                } else {
                    if(this.y > pointY) {
                        this.y--;
                    } else {
                        this.y++;
                    }
                }
            }
            
            danger[this.x][this.y]++;
            
            if(this.x == pointX && this.y == pointY) {
                routeIdx++;
            }
            
            return true;
        }
    }
}