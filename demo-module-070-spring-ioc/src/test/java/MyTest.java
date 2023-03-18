import com.delores.before_ioc.dao.UserDaoImpl;
import com.delores.before_ioc.dao.UserDaoOracleImpl;
import com.delores.before_ioc.service.UserServiceImpl;

/**
 * @author William
 * @date 3/23/21 10:18 PM
 * @description
 */
public class MyTest {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
//        userService.setUserDao(new UserDaoImpl());
        userService.setUserDao(new UserDaoOracleImpl());
        userService.getUser();
    }
}
