package $_001_demo;

/**
 * @author William
 * @date 2022/3/23 3:34 PM
 * @description
 */
public class TT {

    public static void main(String[] args) {

    }


    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 - 0 有序
        // 0 - i 有序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 每个数要比较的次数
        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
}
