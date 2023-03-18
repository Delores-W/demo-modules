package $_002_algorithm.middle;

/**
 * @author William
 * @date 2022/3/29 1:07 PM
 * @description
 */
public class BSExist {

    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }

        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            // mid = (L + R) / 2
            // L 10亿 R 18亿 L + R会导致溢出
            // mid = L + (R - L ) / 2
            // N / 2   N >> 1
            mid = L + (R - L) >> 1;
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }

    public static void main(String[] args) {

        int[] arr = {7, 13, 33, 87, 97, 99, 117, 123, 129, 131, 137};

        int[] arr2 = new int[]{7, 13, 33, 87, 97, 99, 117, 123, 129, 131, 137};

        int[] arr3 = new int[5];

        exist(arr, 13);
    }
}
