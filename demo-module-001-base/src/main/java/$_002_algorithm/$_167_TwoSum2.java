package $_002_algorithm;

import java.util.Arrays;

/**
 * @author William
 * @date 8/20/21 10:37 PM
 * @description
 */
public class $_167_TwoSum2 {

    public static void main(String[] args) {

        int[] numbers = new int[]{2,7,11,15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(numbers, target)));

        System.out.println(test());


    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                ++left;
            } else {
                --right;
            }
        }
        return new int[]{-1, -1};
    }

    public static int test() {
        int x = 1;
        int y = ++x;
        return y;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m -1, p2 = n - 1;
        int tail = m + n - 1;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                nums1[tail] = nums2[p2--];
            } else if (p2 == -1) {
                nums1[tail] = nums1[p1--];
            } else if (nums1[p1] < nums2[p2]) {
                nums1[tail] = nums2[p2--];
            } else {
                nums1[tail] = nums1[p1--];
            }
            --tail;
        }
    }
}
