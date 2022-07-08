import java.util.Arrays;
import java.util.Scanner;

public class GreedyTimetable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int start = 1;
        int end = 1440;
        /*System.out.println("Введите колл-во событий");*/
        int count = in.nextInt();
        int[][] timeTable = new int[count][2];
        /*System.out.println("Введите временные рамки для каждого события (begin end)");*/
        for (int i = 0; i < count; i++) {
            timeTable[i][0] = in.nextInt();
            timeTable[i][1] = in.nextInt();
        }

        int[][] bestChoice = new int[count][2];
        int realtime = start;
        boolean canContinue = true;
        int countBest = 0;
        int min;
        int minId = 0;

        while (canContinue) {
            canContinue = false;
            min = end + 1;
            for (int i = 0; i < count; i++) {
                if (timeTable[i][1] < min && timeTable[i][0] >= realtime) {
                    min = timeTable[i][1];
                    minId = i;
                    canContinue = true;
                }
            }
            if (canContinue) {
                realtime = timeTable[minId][1];
                bestChoice[countBest++] = timeTable[minId];
            }
        }

        System.out.println(countBest);
        for (int i = 0; i < countBest; i++) {
            System.out.println(Arrays.toString(bestChoice[i]));
        }

    }
}
