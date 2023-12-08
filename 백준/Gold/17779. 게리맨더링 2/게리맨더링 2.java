
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    //오른쪽아래, 왼쪽아래
    final static int[] DX = {1,1};
    final static int[] DY = {1,-1};


    static int n, answer;
    static int[] sum;
    static int[][] map, district;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        answer = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        String[] read;
        for(int i=0; i<n; i++) {
            read = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(read[j]);
            }
        }

        district = new int[n][n];
        sum = new int[6];
        
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                getMin(x, y);
            }
        }
        System.out.println(answer);
    }

    private static void resetArr() {
        for(int i=0; i<n; i++) {
            Arrays.fill(district[i], 0);
        }
        Arrays.fill(sum, 0);
    }

    private static void getMin(int x, int y) {
        for(int i=1; i<n; i++) {
            for(int j=1; j<n; j++) {
                if(x+i+j>n-1 || y-i<0 || y+j>n-1) continue;
                resetArr();

                district[x][y] = 5;

                int nx = x, ny = y;
                for(int k=0; k<i; k++) {
                    nx += DX[1];
                    ny += DY[1];
                    district[nx][ny] = 5;
                }
                for(int k=0; k<j; k++) {
                    nx += DX[0];
                    ny += DY[0];
                    district[nx][ny] = 5;
                }

                nx = x;ny = y;
                for(int k=0; k<j; k++) {
                    nx += DX[0];
                    ny += DY[0];
                    district[nx][ny] = 5;
                }
                for(int k=0; k<i; k++) {
                    nx += DX[1];
                    ny += DY[1];
                    district[nx][ny] = 5;
                }

                for(int k=0; k<n; k++) {
                    int left = 0, right = n-1;
                    while(left < right) {
                        if(district[k][left] != 5) {
                            left++;
                        }
                        if(district[k][right] != 5) {
                            right--;
                        }
                        if(district[k][left] == 5 && district[k][right] == 5) {
                            break;
                        }
                    }
                    if(left == right) continue;

                    for(int z=left+1; z<right; z++) {
                        district[k][z] = 5;
                    }
                }

                //구역 구분
                for(int k=0; k<n; k++) {
                    for(int z=0; z<n; z++) {
                        if(district[k][z] == 5) continue;

                        if(k<x+i && z<=y) {
                            district[k][z] = 1;
                        } else if(k<=x+j && y<z) {
                            district[k][z] = 2;
                        } else if(x+i<=k && z<y-i+j) {
                            district[k][z] = 3;
                        } else if(x+j< k && y-i+j<= z) {
                            district[k][z] = 4;
                        }
                    }
                }

                //각 구역 합 구하기
                for(int k=0; k<n; k++) {
                    for(int z=0; z<n; z++) {
                        sum[district[k][z]] += map[k][z];
                    }
                }
                
                //최대, 최소 차 구하기
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for(int k=1; k<=5; k++) {
                    int cur = sum[k];
                    if(cur < min) {
                        min = cur;
                    }
                    if(cur > max) {
                        max = cur;
                    }
                }
                //최솟값 구하기
                answer = Math.min(answer, (max - min));
            }
        }
    }
}