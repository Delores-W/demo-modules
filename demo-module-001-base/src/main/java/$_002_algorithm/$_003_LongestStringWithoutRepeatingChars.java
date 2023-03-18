package $_002_algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author William
 * @date 2020/9/30 5:18 PM
 * @description
 */
public class $_003_LongestStringWithoutRepeatingChars {

    public static void main(String[] args) {

        String str = "abdcdssjlfsdddsjlkl";
        int result = lengthOfLongestSubstring(str);
        System.out.println(result);
    }


    public static int lengthOfLongestSubstring(String s) {

        Set<Character> chars = new HashSet<>();
        // 移动窗口

        // 右指针
        int rp = 0, ml = 0;
        for (int i = 0; i < s.length(); i++) {
            // 左指针 i 向右推移
            if (i != 0) {
                chars.remove(s.charAt(i - 1));
            }

            // 不重复的话 添加进集合
            while (rp < s.length() && !chars.contains(s.charAt(rp))) {
                chars.add(s.charAt(rp));
                rp++;
            }

            ml = Math.max(rp -i , ml);

        }

        return ml;

    }
}
