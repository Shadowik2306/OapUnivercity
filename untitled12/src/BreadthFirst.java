import java.util.Arrays;
import java.util.Scanner;

public class BreadthFirst {
    static void fun(int[][] matrix) {
        int size = 1;
        int point = 0;
        int[] result = new int[matrix[0].length];
        boolean[] visited = new boolean[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            visited[i] = true;
        }
        visited[0] = false;
        result[0] = point;
        for (int i = 0; i < matrix[0].length; i++) {
            if (i == size){
                break;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[result[i]][j] == 1 && visited[j]) {
                    result[size++] = j;
                    visited[j] = false;
                }
            }
        }

        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
        int[][] lst = new int[8][8];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                lst[i][j] = in.nextInt();
            }
        }
        fun(lst);
    }
}

/*      0 0 0 1 0 0 0 0
        0 0 0 1 0 0 0 0
        1 0 0 0 1 1 0 0
        0 1 0 0 0 1 0 1
        0 0 0 0 0 0 0 1
        0 0 0 0 0 0 0 0
        0 0 0 0 0 1 0 0
        0 0 0 0 0 0 1 0   */

/*
[0, 3, 1, 5, 7, 6, 0, 0]*/
