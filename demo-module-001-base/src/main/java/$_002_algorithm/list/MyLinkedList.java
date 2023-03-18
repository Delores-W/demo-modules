package $_002_algorithm.list;

import org.w3c.dom.Node;

import java.util.LinkedList;

/**
 * @author William
 * @date 2022/7/11 15:50
 * @description
 */
public class MyLinkedList<E> {

    transient int size = 0;

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;

    public MyLinkedList() {
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    /**
     *
     * @param e
     * @description 与上面的方法一样
     */
    void linkLastOrigin(E e) {
        if (last == null) {
            last = new Node<>(null, e, null);
            first = last;
        } else {
            Node<E> newNode = new Node<>(last, e, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }
}
