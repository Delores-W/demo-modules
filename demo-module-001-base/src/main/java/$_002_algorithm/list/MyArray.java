package $_002_algorithm.list;

import java.util.Arrays;

/**
 * @author William
 * @date 2022/7/6 18:57
 * @description
 */
public class MyArray {

    private static final int DEFAULT_CAPACITY = 10;

    private static final int[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static final int[] EMPTY_ELEMENTDATA = {};

    // data 数组长度自定义
    private int[] data;

    // 动态数组中已经存储的元素个数
    private int size;

    public MyArray() {
        // 默认开辟10个大小数组长度，在add()时创建
        this.data = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArray(int initialCapacity) {
        // 自定义初始数组长度
        // this.data = new int[initCap];

        if (initialCapacity > 0) {
            this.data = new int[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * 在当前数组中添加一个新元素
     *
     * @param val add value
     */
    public boolean add(int val) {
        // size 默认为0，因为是int
        ensureCapacityInternal(size + 1);
        data[size++] = val;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(data, minCapacity));
    }

    private static int calculateCapacity(int[] data, int minCapacity) {
        if (data == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }


    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - data.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        data = Arrays.copyOf(data, newCapacity);
    }

    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public void add(int index, int value) {
        // 确保插入位置小于当前数组长度
        rangeCheck(index);

        // 确保数组已使用长度（size）加1之后足够存下 下一个数据
        // 如果当前数组已使用长度（size）加1后的大于当前的数组长度，则调用grow方法，增长数组
        // grow方法会将当前数组的长度变为原来容量的1.5倍。
        ensureCapacityInternal(size + 1);

        // 确保有足够的容量之后，使用System.arraycopy 将需要插入的位置（index）后面的元素统统往后移动一位。
        System.arraycopy(data, index, data, index + 1,
                size - index);
        // 将新的数据内容存放到数组的指定位置（index）上
        data[index] = value;
        size++;
    }

    public int get(int index) {
        rangeCheck(index);

        return data[index];
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}



