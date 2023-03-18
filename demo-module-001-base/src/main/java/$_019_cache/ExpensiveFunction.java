package $_019_cache;

/**
 * @author William
 * @date 12/15/20 1:40 AM
 * @description 耗时计算的实现类，实现了Computable接口，
 * 但本身不具备也不需要考虑缓存的事情
 */
public class ExpensiveFunction implements Computable<String, Integer>{
    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(3000);
        System.out.println(arg + " 进行计算");
        return Integer.parseInt(arg);
    }
}
