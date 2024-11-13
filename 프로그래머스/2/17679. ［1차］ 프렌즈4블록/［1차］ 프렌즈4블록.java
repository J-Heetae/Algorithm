import java.util.*;

class Solution {
    
    static int row, col;
    static char[][] arr;
    
    public int solution(int m, int n, String[] board) {
        row = board.length;
        col = board[0].length();
        arr = new char[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        int removed = 0;
        while(true) {
            int remove = findPair();
            if(remove == 0) {
                break;
            }
            removed += remove;
            
        }
        return removed;
    }
    
    int findPair() {
        int[] dx = {0,1,1};
        int[] dy = {1,1,0};
        
        int totalFind = 0;
        boolean[][] check = new boolean[row][col];
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                ArrayList<int[]> find = new ArrayList<>();
                if(arr[i][j] != '-') {
                    find.add(new int[]{i,j});
                    char compare = arr[i][j];
                    for(int k=0; k<3; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if(nx == row || ny == col || compare != arr[nx][ny]) {
                            break;
                        }
                        find.add(new int[]{nx,ny});
                    }
                }
                
                if(find.size() == 4) {
                    for(int[] coor : find) {
                        if(!check[coor[0]][coor[1]]) {
                            totalFind++;
                        }
                        check[coor[0]][coor[1]] = true;
                    }
                }
            }
        }
        
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(check[i][j]) {
                    arr[i][j] = '-';
                }
            }
        }
        for(int i=0; i<col; i++) {
            for(int j=row - 1; j>=0; j--) {
                if(arr[j][i] == '-') {
                    continue;
                }
                int nextIdx = j;
                while(nextIdx + 1 < row && arr[nextIdx + 1][i] == '-') {
                    nextIdx++;
                }
                if(j != nextIdx) {
                    arr[nextIdx][i] = arr[j][i];
                    arr[j][i] = '-';
                }
            }
        }
        return totalFind;
    }
}