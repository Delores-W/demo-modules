package $_002_algorithm.tens;

import $_002_algorithm.utils.Utils;

import java.util.Arrays;

/**
 * @author William
 * @date 2022/3/24 3:31 PM
 * @description
 */
public class BubbleSort {

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Utils.generateRandomArray(maxSize, maxValue);
            // System.out.println("Origin Array: \n" + Arrays.toString(arr1));
            int[] arr2 = Utils.copyArray(arr1);
            bubbleSort(arr1);
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

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - i -1; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    swap(arr, j, j + 1);
//                }
//            }
//        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
