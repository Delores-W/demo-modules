package $_002_algorithm.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author William
 * @date 2022/7/9 23:01
 * @description
 */
public class Test {

    public static void main(String[] args) {
        MyArray list = new MyArray();
        list.add(10);
        list.add(11);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
        list2.add(10);

        MyLinkedList linkedList = new MyLinkedList<>();
        linkedList.add(10);


        Stack stack = new Stack<>();
        stack.push("Delores");
        stack.pop();
    }
}
