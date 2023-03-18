package clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author William
 * @date 6/13/21 10:20 AM
 * @description
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Father father = new Father();
        father.setName("Delores");
        father.setAge(20);
        Child child = new Child("William", 10);
        father.setChild(child);

        Father cloneFather = (Father) father.clone();

        System.out.println(father.toString());
        // Father(name=Delores, age=20, child=Child(name=William, age=10))
        System.out.println(cloneFather.toString());
        // Father(name=Delores, age=20, child=Child(name=William, age=10))
        father.setName("Jack");
        System.out.println(cloneFather.toString());
        // Father(name=Delores, age=20, child=Child(name=William, age=10))

        child.setName("sss");
        System.out.println(cloneFather.toString());
        // Father(name=Delores, age=20, child=Child(name=William, age=10))

    }
}

@Data
class Father implements Cloneable {
    private String name;
    private int age;
    private Child child;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Father father = (Father) super.clone();
        father.setChild((Child) child.clone());
        return father;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Child implements Cloneable {
    private String name;
    private int age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}