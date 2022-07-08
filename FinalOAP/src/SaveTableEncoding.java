public class SaveTableEncoding {
    int[] indexReal = new int[2];
    String value;

    int[] indexLast = new int[2];
    int weightOfItem;
    int[] posWeightItem;

    boolean forward;

    static int finalPrice = 0;
    static int[] finalItems;
    static int countOfItems;

    SaveTableEncoding(int[] indexReal, String value, int weightOfItem, int[] posWeightItem, boolean forward) {
        this.indexReal = indexReal;
        this.value = value;
        if (indexReal[1] == 0) {
            indexLast = new int[]{-2, -2};
        }
        else {
            indexLast = new int[]{indexReal[0] - 1, indexReal[1]};
        }
        this.weightOfItem = weightOfItem;
        this.posWeightItem = posWeightItem;
        this.forward = forward;
    }

    SaveTableEncoding() {
        int[] a = {-1, -1};
        new SaveTableEncoding(a, "0", 0, new int[]{-2, -2}, true);
    }


    public String getValue() {
        return value;
    }

    public int[] getIndexReal() {
        return indexReal;
    }

    static void loadAnswer(int price){
        finalPrice = price;
    }
}
