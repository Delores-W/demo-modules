import com.delores.mapper.DepartmentMapper;
import com.delores.mapper.EmployeeMapper;
import com.delores.pojo.Department;
import com.delores.pojo.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author William
 * @date 3/26/21 6:01 PM
 * @description
 */
public class TestMybatis {

    @Test
    public void test() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                Employee employee = session.selectOne("com.delores.mapper.EmployeeMapper.selectEmployee", 1);
                System.out.println(employee.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
                System.out.println(employeeMapper);
                // org.apache.ibatis.binding.MapperProxy@1f57539
                // 实现类是由mybatis 生成的动态代理类
                Employee employee = employeeMapper.getEmployee(1);
                System.out.println(employee);
                session.commit();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParam() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
//            Employee emp = new Employee();
////            emp.setId(1);
//            emp.setLastName("sss");
//            Employee employee = mapper.findOne(emp);
//            System.out.println(employee);
//            Employee employee = mapper.getEmployeeByName(1, "Wang");
//            System.out.println(employee.getLastName());
//            System.out.println("----------------------------------");
//            System.out.println(employee.getDept().getId());



//          // SELECT * FROM delores.employee e, delores.department dep WHERE e.dep_id = dep.id order by last_name LIMIT ?, ?
            // ？(从第几条开始) ， ？（查几条）
            Page<Object> page = PageHelper.startPage(3, 2, "last_name");
            List<Employee> list = mapper.getEmployeeWithDept();
            list.forEach(System.out :: println);
            System.out.println("总条数 " + page.getTotal());
            System.out.println("总页码 " + page.getPages());


//            Employee employee = list.get(1);
//            System.out.println(employee.getId() + "--" +  employee.getEmail());

//            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
//            Department department = departmentMapper.getDepartmentPlus(1);
//            Department department = departmentMapper.getDepartment(1);
//            System.out.println(department.getName());
        }
    }
}
