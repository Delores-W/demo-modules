package $_008_list;

/**
 * @author William
 * @date 2020/9/9 10:47 AM
 * @description
 */
public class Test {

    public static void main(String[] args) {

        // 需要复杂的逻辑操作才能使用链表

        Node<String> node1 = new Node<>("Delores");
        Node<String> node2 = new Node<>("William");
        Node<String> node3 = new Node<>("Frank");
        Node<String> node4 = new Node<>("Jack");

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
    }


}

class Node<E> {

    private E data;
    private Node<E> next;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
