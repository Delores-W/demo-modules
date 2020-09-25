package compare;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author William
 * @date 2020/9/17 2:33 PM
 * @description
 */
public class Test {

    public static void main(String[] args) {

        Integer[] array = new Integer[]{2, 5, 1, 10, 7};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        Phone[] per = new Phone[]{
                new Phone("Apple", 20.0),
                new Phone("Google", 30.0),
                new Phone("Huawei", 10.0)
        };

        Arrays.sort(per, new PhoneComparator());
        System.out.println(Arrays.toString(per));

        System.out.println("********************************");

        BinaryTree<Person> bt = new BinaryTree<>();
        bt.add(new Person("80", 80));
//        bt.add(new Person("50", 50));
        bt.add(new Person("90", 90));
//        bt.add(new Person("60", 60));
//        bt.add(new Person("30", 30));
//        bt.add(new Person("55", 55));
        bt.add(new Person("85", 85));
        bt.add(new Person("95", 95));


        List<Person> list = bt.toList();

        list.forEach(System.out::println);
        // ********************************
        // Person{name='A', age=3}
        // Person{name='B', age=5}
        // Person{name='C', age=10}
        // Person{name='D', age=20}
        // Person{name='F', age=30}
        // Person{name='E', age=30}
        // Person{name='G', age=40}
        // Person{name='H', age=70}
        System.out.println("-----------------------------------");
//        bt.remove(new Person("90", 90));
//        bt.remove(new Person("95", 95));
//        bt.remove(new Person("85", 85));
//        bt.remove(new Person("30", 30));
//        bt.remove(new Person("60", 60));
        bt.remove(new Person("80", 80));
        List<Person> list2 = bt.toList();
        if (list2 != null)
        list2.forEach(System.out::println);


    }
}

/**
 * 实现二叉树操作
 *
 * @param <T>
 */
class BinaryTree<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node parent;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
        }

        public void addNode(Node newNode) {
            if (newNode.data.compareTo(this.data) <= 0) {
                if (this.left == null) {
                    this.left = newNode;
                    newNode.parent = this;
                } else {
                    this.left.addNode(newNode);
                }
            } else {
                if (this.right == null) {
                    this.right = newNode;
                    newNode.parent = this;
                } else {
                    this.right.addNode(newNode);
                }
            }
        }

        public List<T> toList() {
            // 中序遍历
            if (this.left != null) {
                this.left.toList();
            }
            list.add(this.data);
            if (this.right != null) {
                this.right.toList();
            }
            return list;
        }

        /**
         * @param data data
         * @return boolean
         */
        public Node getNode(T data) {
            if (data.compareTo(this.data) == 0) {
                return this;
            } else if (data.compareTo(this.data) < 0) {
                if (this.left != null) {
                    return this.left.getNode(data);
                } else {
                    return null;
                }
            } else {
                if (this.right != null) {
                    return this.right.getNode(data);
                } else {
                    return null;
                }
            }
        }

    }

    // 以下为二叉树功能实现
    private Node root;
    private int count;
    private List<T> list;

    /**
     * @param data 要保存数据的内容
     * @throws NullPointerException 保存数据为null时抛出
     */
    public void add(T data) {
        if (data == null)
            throw new NullPointerException("保存数据为null");
        Node newNode = new Node(data);
        if (this.root == null) {
            this.root = newNode;
        } else { // 为其保存到一个合适的节点
            this.root.addNode(newNode);
        }
        this.count++;
    }

    public List<T> toList() {
        if (this.count == 0) {
            return null;
        } else {
            this.list = new ArrayList<>();
            return this.root.toList();
        }
    }

    public boolean contains(T data) {
        if (this.count == 0) return false;
        return this.root.getNode(data) != null;
    }

    public void remove(T data) {
        Node removeNode = this.root.getNode(data);

        if (removeNode != null && removeNode == this.root) { // 非空根节点
            if (removeNode.left == null && removeNode.right == null) { // 根节点没有子节点
                this.root = null;
            } else if (removeNode.left != null && removeNode.right == null) { // 只有左节点
                removeNode.left.parent = null;
                this.root = removeNode.left;
            } else if (removeNode.left == null) { // 只有右节点
                removeNode.right.parent = null;
                this.root = removeNode.right;
            } else {
                Node moveNode = removeNode.right;
                if (moveNode.left == null) { // 右子节点没有更小的子节点，右子节点为要移动的节点
                    moveNode.parent = null;
                    this.root = moveNode;
                } else {
                    while (moveNode.left != null) {
                        moveNode = moveNode.left; // 一直向左找到最左边的节点
                    }
                    moveNode.parent.left = null;
                    moveNode.parent = null;
                    moveNode.left = removeNode.left; // 为移动的节点建立新的连接
                    moveNode.right = removeNode.right;
                    removeNode.left.parent = moveNode;
                    removeNode.right.parent = moveNode;
                    this.root = moveNode;
                }
            }
            this.count--;
        } else if (removeNode != null && removeNode.parent.left == removeNode) { // 非空 非根 左节点
            if (removeNode.left == null && removeNode.right == null) { // 没有任何子节点 为叶子节点
                removeNode.parent.left = null; // 更新父节点的引用
            } else if (removeNode.left != null && removeNode.right == null) { // 只有左节点
                removeNode.parent.left = removeNode.left; // 更新父节点和左子节点的引用
                removeNode.left.parent = removeNode.parent;
            } else if (removeNode.left == null && removeNode.right != null) { // 只有右节点
                removeNode.parent.left = removeNode.right;
                removeNode.right.parent = removeNode.parent;
            } else { // 左右都有节点，将右边节点的最左边的节点找到，改变其指向 --- 找到要删除节点的右子节点的最小子节点
                Node moveNode = removeNode.right;
                if (moveNode.left == null) { // 右子节点没有更小的子节点，右子节点为要移动的节点
                    moveNode.parent = removeNode.parent;
                    moveNode.left = removeNode.left;
                    removeNode.parent.left = moveNode;
                    removeNode.left.parent = moveNode;
                } else {
                    while (moveNode.left != null) {
                        moveNode = moveNode.left; // 一直向左找到最左边的节点
                    }
                    moveNode.parent.left = null;
                    moveNode.parent = removeNode.parent;
                    moveNode.left = removeNode.left; // 为移动的节点建立新的连接
                    moveNode.right = removeNode.right;
                    removeNode.parent.left = moveNode; // 为删除节点的父节点建立新的连接
                    removeNode.left.parent = moveNode; // 为删除节点的子节点建立新的连接
                }
            }
            this.count--;
        } else if (removeNode != null && removeNode.parent.right == removeNode) { // 非空 非根 右节点
            if (removeNode.left == null && removeNode.right == null) { // 没有任何子节点 为叶子节点
                removeNode.parent.right = null; // 更新父节点的引用
            } else if (removeNode.left != null && removeNode.right == null) { // 只有左节点
                removeNode.parent.right = removeNode.left; // 更新父节点和左子节点的引用
                removeNode.left.parent = removeNode.parent;
            } else if (removeNode.left == null && removeNode.right != null) { // 只有右节点
                removeNode.parent.right = removeNode.right;
                removeNode.right.parent = removeNode.parent;
            } else { // 左右都有节点，将右边节点的最左边的节点找到，改变其指向 --- 找到要删除节点的右子节点的最小子节点
                Node moveNode = removeNode.right;
                if (moveNode.left == null) { // 右子节点没有更小的子节点，右子节点为要移动的节点
                    moveNode.parent = removeNode.parent;
                    moveNode.left = removeNode.left;
                    removeNode.parent.right = moveNode;
                    removeNode.left.parent = moveNode;
                } else {
                    while (moveNode.left != null) {
                        moveNode = moveNode.left; // 一直向左找到最左边的节点
                    }
                    moveNode.parent.left = null; // 先断开连接
                    moveNode.parent = removeNode.parent;
                    moveNode.left = removeNode.left; // 为移动的节点建立新的连接
                    moveNode.right = removeNode.right;
                    removeNode.parent.right = moveNode; // 为删除节点的父节点建立新的连接
                    removeNode.left.parent = moveNode; // 为删除节点的子节点建立新的连接
                }
            }
            this.count--;
        }
    }

}


class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        // 大于 0
        // 小于 0
        // 等于 0
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class PhoneComparator implements Comparator<Phone> {
    @Override
    public int compare(Phone o1, Phone o2) {
        if (o1.getPrice() - o2.getPrice() > 0) {
            return 1;
        } else if (o1.getPrice() - o2.getPrice() < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Phone {
    private String name;
    private Double price;

    public Phone(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}



