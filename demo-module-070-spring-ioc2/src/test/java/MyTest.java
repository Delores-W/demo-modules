import com.delores.config.AppConfig;
import com.delores.config.ThirdPartClass;
import com.delores.config.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author William
 * @date 3/25/21 4:55 PM
 * @description
 */
public class MyTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("*****************");
        User user = applicationContext.getBean(User.class);
        System.out.println(user.toString());
    }

    @Test
    public void testBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        ThirdPartClass thirdPartClass = applicationContext.getBean(ThirdPartClass.class);
//        bean的名字和注册bean的方法名相同
        ThirdPartClass thirdPartClass = applicationContext.getBean(ThirdPartClass.class);
        System.out.println(thirdPartClass);
    }
}
