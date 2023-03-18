import com.delores.aop.service.AppConfig;
import com.delores.aop.service.UserService;
import com.delores.aop.service.UserServiceImpl;
import com.delores.dynamic_proxy.Message;
import com.delores.dynamic_proxy.ProxyFactory;
import com.delores.dynamic_proxy.ProxyFactory2;
import com.delores.static_proxy.Agency;
import com.delores.static_proxy.Landlord;
import com.delores.static_proxy.Rent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author William
 * @date 3/25/21 9:29 PM
 * @description
 */
public class TestProxy {

    @Test
    public void testStaticProxy() {
        Agency agency = new Agency(new Landlord());
        agency.rent();
    }

    @Test
    public void testDynamicProxy() {
        Rent rent = (Rent) new ProxyFactory(new Landlord()).getProxyInstance();
        rent.rent();
    }

    @Test
    public void testDynamicProxy2() {
        Message message = (Message) new ProxyFactory2(new Message()).getProxyInstance();
        message.sendMsg();
    }

    @Test
    public void testAop() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.add();
        userService.delete();
    }

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = applicationContext.getBean(UserService.class);
        bean.add();
        bean.delete();
        bean.select();
    }
}
