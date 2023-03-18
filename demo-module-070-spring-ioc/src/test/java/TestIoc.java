import com.delores.ioc.Hello;
import com.delores.ioc.Student;
import com.delores.ioc.autowire.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author William
 * @date 3/24/21 12:37 AM
 * @description
 */
public class TestIoc {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("autowire.beans.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Student stu = (Student) context.getBean("student");
//        System.out.println(stu.toString());

        People people = context.getBean(People.class);
        System.out.println(people.toString());
    }
}
