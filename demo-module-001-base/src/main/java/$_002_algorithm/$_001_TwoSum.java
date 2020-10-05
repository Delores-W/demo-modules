package $_002_algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 * @date 2020/9/21 11:00 AM
 * @description
 */
public class $_001_TwoSum {

    public static void main(String[] args) {
        int[] nums = { 7, 9, 5, 20, 2, 7, 7};
        int target = 14;

        int[] result = twoSum(nums, target);
        int[] result2 = twoSumMap(nums, target);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));

        System.out.println("--------------------------------------");

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                System.out.println(nums[i] + "---" + nums[j]);
            }
        }

        System.out.println("--------------------------------------");

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]){
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));


    }



    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[j] == target - nums[i])
                    return new int[]{i, j};
            }
        }
        throw new RuntimeException("Fail");
    }

    public static int[] twoSumMap(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new RuntimeException("Fail");
    }
}
