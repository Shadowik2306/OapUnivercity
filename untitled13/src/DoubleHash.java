import java.util.*;

public class DoubleHash {
    int PRIME = 7;
    private int MAX_SIZE = 13;
    private int currrentSize = 0;
    int[][] hashTable;
    HashSet<Integer> keys = new HashSet<Integer>();

    DoubleHash() {
        hashTable = new int[MAX_SIZE][2];
        for (int i = 0; i < MAX_SIZE; i++) {
            hashTable[i][1] = -1;
        }
    }

    private boolean isRehashing() {
        return ((float)currrentSize / (float)MAX_SIZE) * 100 > 70;
    }

    private int hash1(int key) {
        return key % MAX_SIZE;
    }

    private int hash2(int key) {
        return PRIME - (key % PRIME);
    }

    private void rehashing() {
        int[][] realTable = hashTable.clone();
        MAX_SIZE *= 2;
        hashTable = new int[MAX_SIZE][2];
        for (int i = 0; i < MAX_SIZE; i++) {
            hashTable[i][1] = -1;
        }
        for (int[] item: realTable) {
            int ind = hash1(item[0]);
            if (hashTable[ind][1] != -1) {
                int extraInd = hash2(item[0]);
                int j = 1;
                while (true) {
                    int newInd = (ind + j * extraInd) % MAX_SIZE;
                    if (hashTable[newInd][1] == -1) {
                        hashTable[newInd][0] = item[0];
                        hashTable[newInd][1] = item[1];
                        break;
                    }
                    j++;
                }
            }
            else {
                hashTable[ind][0] = item[0];
                hashTable[ind][1] = item[1];
            }
        }
    }

    public void insertValue(int key, int value) {
        if (keys.contains(key)) {
            changeValue(key, value);
            return;
        }

        keys.add(key);
        currrentSize++;
        int ind = hash1(key);

        if (hashTable[ind][1] != -1) {
            int extraInd = hash2(key);
            int i = 1;
            while (true) {
                int newInd = (ind + i * extraInd) % MAX_SIZE;
                if (hashTable[newInd][1] == -1) {
                    hashTable[newInd][0] = key;
                    hashTable[newInd][1] = value;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable[ind][0] = key;
            hashTable[ind][1] = value;
        }
        if (isRehashing()) rehashing();
    }

    public void printTable() {
        for (int i = 0; i < MAX_SIZE; i++) {
            System.out.println(hashTable[i][0] + "-->" + hashTable[i][1]);
        }
    }

    public int takeElem(int key) {
        if (!keys.contains(key)) {
            System.out.println("Не содержит данных об этом ключе");
            return -1;
        }

        int ind = hash1(key);

        if (hashTable[ind][0] != key) {
            int extraInd = hash2(key);
            int i = 1;
            while (true) {
                int newInd = (ind + i * extraInd) % MAX_SIZE;
                if (hashTable[newInd][0] == key) {
                    return hashTable[newInd][1];
                }
                i++;
            }
        }
        else {
            return hashTable[ind][1];
        }
    }

    public void deleteKey(int key) {
        if (!keys.contains(key)) {
            System.out.println("Не содержит данных об этом ключе");
            return;
        }

        keys.remove(key);
        currrentSize--;
        int ind = hash1(key);

        if (hashTable[ind][0] != key) {
            int extraInd = hash2(key);
            int i = 1;
            while (true) {
                int newInd = (ind + i * extraInd) % MAX_SIZE;
                if (hashTable[newInd][0] == key) {
                    hashTable[newInd][1] = -1;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable[ind][1] = -1;
        }
    }

    public void changeValue(int key, int value) {
        if (!keys.contains(key)) {
            System.out.println("Не содержит данных об этом ключе");
            return;
        }

        int ind = hash1(key);

        if (hashTable[ind][0] != key) {
            int extraInd = hash2(key);
            int i = 1;
            while (true) {
                int newInd = (ind + i * extraInd) % MAX_SIZE;
                if (hashTable[newInd][0] == key) {
                    hashTable[newInd][1] = value;
                    break;
                }
                i++;
            }
        }
        else {
            hashTable[ind][1] = value;
        }
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        DoubleHash h;

        HashSet<Integer> n;
        long start_time;
        for (int j = 1; j < 26; j++) {
            h = new DoubleHash();
            n = new HashSet<Integer>();
            System.out.print(Integer.toString((int)Math.pow(2, j)) + '\t');
            start_time = System.currentTimeMillis();
            for (int i = 0; i < (int)Math.pow(2, j); i++) {
                int k = (int) (Math.random() * 100000000);
                h.insertValue(k, 1);
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
    }
}
