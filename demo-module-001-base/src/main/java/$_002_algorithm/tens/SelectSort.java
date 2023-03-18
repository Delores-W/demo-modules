package $_002_algorithm.tens;

import $_002_algorithm.utils.Utils;

import java.util.Arrays;

/**
 * @author William
 * @date 2022/3/24 4:12 PM
 * @description
 */
public class SelectSort {

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 5;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = Utils.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Utils.copyArray(arr1);
            selectSort(arr1);
            Utils.comparator(arr2);
            if (!Utils.isEqual(arr1, arr2)) {
                succeed = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking wrong...");
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 - N
        // 1 - N
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
