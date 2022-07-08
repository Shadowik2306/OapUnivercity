import java.util.Scanner;

public class BackpackDynamic {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int space = in.nextInt();
        int count = in.nextInt();
        int[] weigh = new int[count];
        for (int i = 0; i < count; i++) weigh[i] = in.nextInt();
        int[] price = new int[count];
        for (int i = 0; i < count; i++) price[i] = in.nextInt();

        int[][] arr = new int[count + 1][space + 1];

        for (int i = 0; i <= count; i++) {
            for (int j = 0; j <= space; j++) {
                if (i == 0 || j == 0) arr[i][j] = 0;
                else {
                    if (j >= weigh[i - 1]) {
                        arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - weigh[i - 1]] + price[i - 1]);
                    }
                    else {
                        arr[i][j] = arr[i - 1][j];
                    }
                }
            }
        }
        parseTable(arr, weigh, count, space);


    }

    private static void parseTable(int[][] arr, int[] weight, int i, int j) {
        if (arr[i][j] == 0) return;
        if (arr[i][j] == arr[i - 1][j]) parseTable(arr, weight, i - 1, j);
        else {
            parseTable(arr, weight, i - 1, j - weight[i - 1]);
            System.out.println(i);
        }

    }
}
