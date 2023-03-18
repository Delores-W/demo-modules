package $_019_cache;

/**
 * @author William
 * @date 12/15/20 1:24 AM
 * @description 有一个计算函数compute(), 用来代表耗时计算
 * 每个计算器都要实现这个接口，这样可以无侵入实现缓存功能
 */
public interface Computable<A, V> {

    V compute(A arg) throws Exception;
}
