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
        bt.add(new Person("D", 20));
        bt.add(new Person("C", 10));
        bt.add(new Person("B", 5));
        bt.add(new Person("A", 3));
        bt.add(new Person("E", 30));
        bt.add(new Person("F", 30));
        bt.add(new Person("G", 40));
        bt.add(new Person("H", 70));

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



