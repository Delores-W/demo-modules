package $_002_algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author William
 * @date 5/27/21 12:07 AM
 * @description
 */
public class $_435_NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return Integer.compare(o1[1], o2[1]);
//            }
//        });

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));


        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }

}
