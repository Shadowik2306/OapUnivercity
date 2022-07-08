import java.util.Scanner;

public class BinaryHeap {
    Node[] heapArray;
    int maxSize = 10000000;
    int currentSize;

    public BinaryHeap() {
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public void insertNode(int key, int value) {
        if (currentSize == maxSize) {
            System.out.println("Массив переполнен");
            return;
        }
        Node node = new Node(key, value);
        heapArray[currentSize] = node;
        updatePlaceUp(currentSize);
        currentSize++;
    }

    private void updatePlaceUp(int key) {
        if (key == 0) {
            return;
        }
        Node node;
        if (heapArray[(key - 1) / 2].getValue() < heapArray[key].getValue()) {
            node = heapArray[(key - 1) / 2];
            heapArray[(key - 1) / 2] = heapArray[key];
            heapArray[key] = node;
            updatePlaceUp((key - 1) / 2);
        }
    }

    private void updatePlaceDown(int key) {
        if (key * 2 > currentSize) {
            return;
        }
        Node node;
        int changeIndex;
        try {
            if (heapArray[key * 2 + 1].getValue() > heapArray[key * 2 + 2].getValue()) {
                changeIndex = key * 2 + 1;
                node = heapArray[key * 2 + 1];
            } else {
                changeIndex = key * 2 + 2;
                node = heapArray[key * 2 + 2];
            }
        } catch (Exception e) {
            return;
        }

        if (node.getValue() > heapArray[key].getValue()) {
            heapArray[changeIndex] = heapArray[key];
            heapArray[key] = node;
            updatePlaceDown(changeIndex);
        }
    }

    public void changeNode(int key, int v) {
        if (currentSize <= key) {
            /*System.out.println("Объекта с таким индексом не существует");*/
            return;
        }
        int old = heapArray[key].getValue();
        heapArray[key].setValue(v);
        if (old < v) {
            updatePlaceUp(key);
        }
        else {
            updatePlaceDown(key);
        }
    }

    public void deleteNode(int key) {
        currentSize--;
        changeNode(key, heapArray[currentSize].getValue());
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
    }

    public int findValue(int key) {
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i].checkKey(key)) {
                return heapArray[i].getValue();
            }
        }
        return -1;
    }
}
