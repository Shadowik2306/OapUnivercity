import java.util.Arrays;
import java.util.Scanner;

public class Dexter {
    public static void fun(int[][][] matrix) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length ; i++) {
            result[i] = 9999;
        }
        boolean[] visited = new boolean[matrix.length];
        for (int i = 0; i < matrix.length ; i++) visited[i] = true;
        int point = 0;
        int mi;
        int mi_point = 0;
        int real_path;
        result[point] = 0;
        visited[point] = false;
        for (int i = 0; i < matrix.length; i++) {
            mi = 9999;
            real_path = result[point];
            visited[point] = false;
            for (int j = 0; j < matrix[point].length; j++) {
                if (real_path + matrix[point][j][1] < result[matrix[point][j][0]]) {
                    result[matrix[point][j][0]] = real_path + matrix[point][j][1];
                    if (mi > real_path + matrix[point][j][1] && visited[matrix[point][j][0]]) {
                        mi = real_path + matrix[point][j][1];
                        mi_point = matrix[point][j][0];
                    }
                }
            }
            if (mi == 9999) {
                for (int j = 0; j < result.length; j++) {
                    if (mi > result[j] && visited[j]) {
                        mi = result[j];
                        mi_point = j;
                    }
                }
                if (mi == 9999) break;
            }
            point = mi_point;
        }
        System.out.println(Arrays.toString(result));
    }

    public static void main(String[] args) {
        int[][][] lst = new int[5][][];
        int p;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            p = in.nextInt();
            lst[i] = new int[p][2];
            for (int j = 0; j < p; j++) {
                lst[i][j][0] = in.nextInt();
                lst[i][j][1] = in.nextInt();
            }
        }
        fun(lst);
    }

}
