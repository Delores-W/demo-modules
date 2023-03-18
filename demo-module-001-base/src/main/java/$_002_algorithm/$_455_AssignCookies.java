package $_002_algorithm;

import java.util.Arrays;

/**
 * @author William
 * @date 5/25/21 4:01 PM
 * @description
 */
public class $_455_AssignCookies {

    public static void main(String[] args) {
        int[] g = {1, 4, 7, 3};
        int[] s = {1, 3, 3, 5};
        int[] candies = new int[3];
        System.out.println(candies[0]);
        Arrays.sort(g);
        System.out.println(Arrays.toString(g));

        System.out.println(findContentChildren(g, s));
    }


    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int numOfChildren = g.length;
        int numOfCookies = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
            while (j < numOfCookies && g[i] > s[j]) {
                j++;
            }
            if (j < numOfCookies) {
                count++;
            }
        }
        return count;
    }
}
