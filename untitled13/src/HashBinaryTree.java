import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class HashBinaryTree {
    int MAX_SIZE = 10;
    int HEAP_SIZE = 10000000;
    BinaryHeap[] hashTable;
    int currentSize = 0;
    HashSet<Integer> keys = new HashSet<Integer>();

    HashBinaryTree() {
        hashTable = new BinaryHeap[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            hashTable[i] = new BinaryHeap();
        }
    }

    private void rehashing() {
        BinaryHeap[] realTable = hashTable.clone();
        MAX_SIZE *= 2;
        HEAP_SIZE *= 2;
        hashTable = new BinaryHeap[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE / 2; i++) {
            for (int item:
                 keys) {
                int ind = hash(item);
                int value = realTable[ind].findValue(item);
                add(item, value);
            }
        }
    }

    private int hash(int key) {
        return key % MAX_SIZE;
    }

    public void add(int key, int value) {
        if (keys.contains(key)) {
            changeValue(key, value);
        }

        else {
            currentSize++;
            keys.add(key);
            int ind = hash(key);
            hashTable[ind].insertNode(key, value);
        }

        /*if ((float) currentSize / (float)HEAP_SIZE * 100 > 50) rehashing();*/
    }

    public void changeValue(int key, int value) {
        if (!keys.contains(key)) {
            return;
        }
        int ind = hash(key);
        hashTable[ind].deleteNode(key);
        hashTable[ind].insertNode(key, value);
    }

    public void deleteKey(int key) {
        if (!keys.contains(key)) {
            return;
        }
        currentSize--;
        int ind = hash(key);
        hashTable[ind].deleteNode(key);
    }

    public void printTable() {
        System.out.println("----------------------------------");
        for (int i = 0; i < MAX_SIZE; i++) {
            hashTable[i].printHeap();
            System.out.println();
            System.out.println("----------------------------------");
        }
    }

    public int getValue(int key) {
        if (keys.contains(key)) {
            return hashTable[hash(key)].findValue(key);
        }
        return -1;
    }

    public static void main(String[] args) {
        HashBinaryTree h;
        HashSet<Integer> n;
        long start_time;
        for (int j = 1; j < 27; j++) {
            h = new HashBinaryTree();
            n = new HashSet<Integer>();
            System.out.print(Integer.toString((int)Math.pow(2, j)) + '\t');
            start_time = System.currentTimeMillis();
            for (int i = 0; i < Math.pow(2, j); i++) {
                int k = (int) (Math.random() * 1000000);
                h.add(k, 1);
                n.add(k);
            }
            System.out.print(Long.toString(System.currentTimeMillis() - start_time) + '\t');
            start_time = System.currentTimeMillis();
            for (int item:
                    n) {
                h.deleteKey(item);
            }
            System.out.println(Long.toString(System.currentTimeMillis() - start_time));
        }

        /*h.printTable();*/

    }
}
