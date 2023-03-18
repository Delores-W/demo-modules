package $_002_algorithm.tens;

import $_002_algorithm.utils.Utils;

import java.util.Arrays;

/**
 * @author William
 * @date 2022/3/24 2:33 PM
 * @description
 */
public class InsertSort {

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Utils.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Utils.copyArray(arr1);
            insertSort(arr1);
            Utils.comparator(arr2);
            if (!Utils.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking wrong...");
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // sorted 0 - 0
        // sort 0 - i
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }

        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
