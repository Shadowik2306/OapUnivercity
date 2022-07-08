import java.util.Arrays;

public class SomeAlgol {
    public static void main(String[] args) {
        bubbleSort();
        System.out.println(fibonacchi(3));
        System.out.println(factorial(5));

    }

    public static void bubbleSort() {
        int[] a = {1,4,6, 2, 4, 1, 3};
        int third;
        for (int i = a.length - 1; i >= 1; i--){
            for (int j = 0; j < i; j++){
                if(a[j] > a[j + 1]) {
                    third = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = third;
                }
            }
        }
        System.out.println("Bubble sort");
        System.out.println(Arrays.toString(a));
    }

    public static int fibonacchi(int n) {
        if (n == 0) {
            return 1;
        }
        else return fibonacchi(n - 1) + fibonacchi(n - 2);
    }

    static int factorial(int n) {
        int num = n;
        if (n == 0)
            return 1;
        num = n * factorial(n - 1);
        return num;
    }

}
