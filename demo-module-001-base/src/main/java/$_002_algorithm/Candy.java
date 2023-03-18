package $_002_algorithm;

/**
 * @author William
 * @date 5/26/21 4:36 PM
 * @description
 */
public class Candy {
    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 2, 3, 3, 3, 2, 1}));
        // 1 2 3 3 3 2 1
        // 1 1 1 1 1 1 1
        // 1 2 3 1 1 1 1
        // 1 2 3 1 3 2 1
    }

    public static int candy(int[] ratings) {

        int numChildren = ratings.length;

        int[] candies = new int[numChildren];

        int count = numChildren;

        for (int i = 0; i < numChildren - 1; i++) {
            if (ratings[i] < ratings[i + 1] && candies[i + 1] <= candies[i]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        for (int j = numChildren - 1; j > 0; j--) {
            if (ratings[j] < ratings[j - 1] && candies[j - 1] <= candies[j]) {
                candies[j - 1] = candies[j] + 1;
            }
        }
        for (int candy : candies) {
            count += candy;
        }
        return count;
    }
}
