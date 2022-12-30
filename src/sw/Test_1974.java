package sw;

import java.util.Scanner;

public class Test_1974 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        //테스트 반복
        for(int i=1; i<=T; i++) {
            int[][] sudoku = new int[9][9];

            //스도쿠 생성
            for(int j=0; j<9; j++) {
                for(int k=0; k<9; k++) {
                    sudoku[j][k] = sc.nextInt();
                }
            }

            
            //가로세로 검사
            if(checkRowColumn(sudoku) == 0) System.out.println("#" + i + " " + 0);
            else {
                //3x3 박스 검사
                if (checkBox(sudoku) == 0) System.out.println("#" + i + " " + 0);
                else System.out.println("#" + i + " " + 1);
            }
        }
    }

    static int checkRowColumn(int[][] sudoku) {

        for(int i=0; i<9; i++) {
            int[] check1 = new int[10];
            int[] check2 = new int[10];

            for(int j=0; j<9; j++) {
                int tmp1 = sudoku[j][i];
                int tmp2 = sudoku[i][j];

                if(check1[tmp1] == 0
                && check2[tmp2] == 0) {
                    check1[tmp1]++;
                    check2[tmp2]++;
                }
                else return 0;
            }
        }

        return 1;
    }

    static int checkBox(int[][] sudoku) {
        for(int i=0; i<9; i++) {
            int row = 0;
            int column = 0;

            while(row != 9) {
                int[] check = new int[10];

                for(int j=0; j<3; j++) {
                    for(int k=0; k<3; k++) {
                        int tmp = sudoku[j+row][k+column];

                        if(check[tmp] == 0) check[tmp]++;
                        else return 0;
                    }
                }

                column += 3;

                if(column == 9) {
                    row += 3;
                    column = 0;
                }
            }
        }

        return 1;
    }
}
