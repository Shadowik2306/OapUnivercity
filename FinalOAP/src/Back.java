import sun.misc.Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Back {
    int[][] arr;
    int spaceBackpack;
    int countOfItems;
    int[] weightsOfItems;
    int[] pricesOfItems;

    static SaveTableEncoding STV = new SaveTableEncoding();

    ArrayDeque<SaveTableEncoding> result = new ArrayDeque<SaveTableEncoding>();
    Back(int spaceBackpack, int countOfItems, int[] weightsOfItems, int[] pricesOfItems) {
        arr = new int[countOfItems + 1][spaceBackpack + 1];
        this.spaceBackpack = spaceBackpack;
        this.countOfItems = countOfItems;
        this.weightsOfItems = weightsOfItems;
        this.pricesOfItems = pricesOfItems;

        for (int i = 0; i <= countOfItems; i++) {
            for (int j = 0; j <= spaceBackpack; j++) {
                int[] k = {i, j};
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                    result.addFirst(new SaveTableEncoding(k, String.valueOf(arr[i][j]), 0, new int[]{-2, -2}, true));
                }
                else {
                    if (j >= weightsOfItems[i - 1]) {
                        arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - weightsOfItems[i - 1]] + pricesOfItems[i - 1]);
                    }
                    else {
                        arr[i][j] = arr[i - 1][j];
                    }
                    result.addFirst(new SaveTableEncoding(k, String.valueOf(arr[i][j]), weightsOfItems[i - 1],
                            new int[] {i - 1,j - weightsOfItems[i - 1]}, true));
                }


            }
        }
        SaveTableEncoding.finalPrice = arr[countOfItems][spaceBackpack];
        SaveTableEncoding.finalItems = new int[countOfItems];
        SaveTableEncoding.countOfItems = 0;
        parseTable();
    }

    public int[][] getArr() {
        return arr;
    }

    public ArrayDeque<SaveTableEncoding> getResult() {
        return result;
    }

    public void parseTable() {
        parseTable(countOfItems, spaceBackpack);
    }


    private void parseTable(int i, int j) {
        if (i - 1 >= 0) {
            result.addFirst(new SaveTableEncoding(new int[]{i, j}, String.valueOf(arr[i][j]), weightsOfItems[i - 1],
                    new int[]{i - 1, j - weightsOfItems[i - 1]}, false));
        }
        else {
            result.addFirst(new SaveTableEncoding(new int[]{i, j}, String.valueOf(arr[i][j]), weightsOfItems[i],
                    new int[]{i - 1, j - weightsOfItems[i]}, false));
        }
        if (arr[i][j] == 0) return;
        if (arr[i][j] == arr[i - 1][j]) parseTable(i - 1, j);
        else {
            parseTable(i - 1, j - weightsOfItems[i - 1]);
            SaveTableEncoding.finalItems[SaveTableEncoding.countOfItems] = i;
            SaveTableEncoding.countOfItems++;
        }

    }

    public static void main(String[] args) {
        long start_time;
        for (int j = 1; j < 100; j++) {
            start_time = System.currentTimeMillis();
            int[] weigh = new int[j * 100];
            int[] price = new int[j * 100];
            for (int i = 0; i < j * 100; i++) weigh[i] = (int) (Math.random() * 1000);
            for (int i = 0; i < j * 100; i++) price[i] = (int) (Math.random() * 1000);
            Back n = new Back(j * 100, j * 100, weigh, price);
            System.out.println(j * 100 + "\t" + (System.currentTimeMillis() - start_time));

        }
    }

}
