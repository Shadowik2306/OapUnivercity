import java.util.PriorityQueue;
import java.util.Scanner;

public class BinaryHeap {
/*    Node[] heapArray;
    int maxSize;
    int currentSize;

    public BinaryHeap(int size) {
        maxSize = size;
        currentSize = 0;
        heapArray = new Node[size];
    }

    public void insertNode(int value) {
        if (currentSize == maxSize) {
            System.out.println("Массив переполнен");
            return;
        }
        Node node = new Node(value);
        heapArray[currentSize] = node;
        updatePlaceUp(currentSize);
        currentSize++;
    }

    private void updatePlaceUp(int index) {
        if (index == 0) {
            return;
        }
        Node node;
        if (heapArray[(index - 1) / 2].getValue() < heapArray[index].getValue()) {
            node = heapArray[(index - 1) / 2];
            heapArray[(index - 1) / 2] = heapArray[index];
            heapArray[index] = node;
            updatePlaceUp((index - 1) / 2);
        }
    }

    private void updatePlaceDown(int index) {
        if ((index + 1) * 2 > currentSize) {
            return;
        }
        Node node;
        int changeIndex;
        if (heapArray[index * 2 + 1].getValue() > heapArray[index * 2 + 2].getValue()) {
            changeIndex = index * 2 + 1;
            node = heapArray[index * 2 + 1];
        }
        else {
            changeIndex = index * 2 + 2;
            node = heapArray[index * 2 + 2];
        }

        if (node.getValue() > heapArray[index].getValue()) {
            heapArray[changeIndex] = heapArray[index];
            heapArray[index] = node;
            updatePlaceDown(changeIndex);
        }
    }

    public void changeNode(int index, int v) {
        if (currentSize <= index) {
            return;
        }
        int old = heapArray[index].getValue();
        heapArray[index].setValue(v);
        if (old < v) {
            updatePlaceUp(index);
        }
        else {
            updatePlaceDown(index);
        }
        return;
    }

    public void deleteNode(int index) {
        currentSize--;
        changeNode(index, heapArray[currentSize].getValue());
    }

    public void printHeap() {
        int realCount = 1;
        int count = 1;
        for (int i = 0; i < currentSize; i++) {
            if (realCount == 0) {
                System.out.println();
                count *= 2;
                realCount = count;
            }
            System.out.print(heapArray[i].getValue());
            System.out.print(' ');
            realCount--;
        }
    }*/

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        long start_time;
        for (int j = 0; j < 27; j++) {
            System.out.print(Integer.toString((int)Math.pow(2, j)) + "\t");
            priorityQueue = new PriorityQueue((int)Math.pow(2, j));
            start_time = System.currentTimeMillis();
            for (int i = 0; i < Math.pow(2, j); i++) {
                priorityQueue.add((int)(Math.random() * 100000));
            }
            System.out.print((int) (System.currentTimeMillis() - start_time));
            System.out.print('\t');
            start_time = System.currentTimeMillis();
            System.out.println((int) (System.currentTimeMillis() - start_time));
        }
    }
}
