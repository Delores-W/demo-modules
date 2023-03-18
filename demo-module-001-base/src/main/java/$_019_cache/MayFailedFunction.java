package $_019_cache;

import java.io.IOException;
import java.util.Random;

/**
 * @author William
 * @date 12/16/20 2:08 AM
 * @description
 */
public class MayFailedFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
//        System.out.println("随机数：" + random);
        if (random > 0.5) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        System.out.println("进行计算");
        return Integer.parseInt(arg);
    }
}
