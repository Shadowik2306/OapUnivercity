public class Node {
    private int key;
    private int value;

    public Node(int k, int v) {
        key = k;
        value = v;
    }

    public int getValue() {
        return key;
    }

    public void setValue(int v) {
        value = v;
    }

    public boolean checkKey(int key) {
        return this.key == key;
    }
}