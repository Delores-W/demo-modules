import com.delores.entity.Worker;
import com.delores.entity.n21.Customer;
import com.delores.entity.n21.Order;
import com.delores.entity.n2n.Category;
import com.delores.entity.n2n.Item;
import com.delores.entity.one2one.Department;
import com.delores.entity.one2one.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * @author William
 * @date 4/14/21 4:02 PM
 * @description
 */
public class MyTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void before() {
        // 初始化数据
        initDB();

        //创建服务注册对象
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂对象
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();
    }

    @Test
    public void test() {
        Worker worker = session.get(Worker.class, 1L);
        worker.setName("William");

        Worker worker1 = (Worker) session.createCriteria(Worker.class).uniqueResult();
//        WARN: HHH90000022: Hibernate's legacy org.hibernate.Criteria API is deprecated;
//        use the JPA javax.persistence.criteria.CriteriaQuery instead
        System.out.println(worker1.toString());
        // Worker(id=1, name=William, created=2021-04-14)
    }

    @Test
    public void test2() {
        Worker worker = session.get(Worker.class, 1L);
        System.out.println(worker);
        // Worker(id=1, name=Delores, created=2021-04-14)

        // 此时其它事务（手动更新）数据库这条记录的值
        // 执行refresh() 会从数据库同步最新的内容
        // 前提mysql 事务隔离级别设置为 Read Committed, 可以读取到其它事务更新后的结果
        session.refresh(worker);
        System.out.println(worker);
        // Worker(id=1, name=Delores, created=2021-04-14)
    }

    /**
     * save()
     * 1. 使一个临时对象变为持久化对象
     * 2. 为对象分配ID
     * 3. save方法之前，修改id无效
     * 4. 持久化对象的ID不能被修改
     */
    @Test
    public void testSave() {
        Worker worker = new Worker();
        worker.setName("Jack");
        worker.setCreated(new Date());
        System.out.println(worker);
        // Worker(id=null, name=Jack, created=Thu Apr 15 01:11:10 CST 2021)

        session.save(worker);
        System.out.println(worker);
        // Worker(id=2, name=Jack, created=Thu Apr 15 01:11:10 CST 2021)
    }

    @Test
    public void test_n21() {
        Customer customer = new Customer(0L, "Delores");

        Order order1 = new Order(0L, "AA", new Date(), customer);
        Order order2 = new Order(0L, "BB", new Date(), customer);

        // 先save 1 的对象，可以避免多执行update操作
        session.save(customer);
        session.save(order1);
        session.save(order2);
    }

    @Test
    public void test_n21_get() {
        Order order = session.get(Order.class, 1L);
        System.out.println(order.getOrderName());
        // 默认懒加载，使用到Customer才会查询Customer
        System.out.println("*******************");
        System.out.println(order.getCustomer());
    }

    @Test
    public void test_n21both() {
        com.delores.entity.n21both.Customer customer = new com.delores.entity.n21both.Customer();
        customer.setName("William");

        com.delores.entity.n21both.Order order1 = new com.delores.entity.n21both.Order(0L, "FF", new Date(), customer);
        com.delores.entity.n21both.Order order2 = new com.delores.entity.n21both.Order(0L, "GG", new Date(), customer);

        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        // 先save 1 的对象，可以避免多执行update操作
        session.save(customer);
        session.save(order1);
        session.save(order2);
    }

    @Test
    public void test_n21both_get() {
        com.delores.entity.n21both.Customer customer1 = session.get(com.delores.entity.n21both.Customer.class, 1L);
        com.delores.entity.n21both.Customer customer2 = session.get(com.delores.entity.n21both.Customer.class, 2L);
        System.out.println(customer1.getName());
        System.out.println("****************");
        // 默认懒加载
        for (com.delores.entity.n21both.Order order : customer1.getOrders()) {
            System.out.println(order);
        }

        if (customer1.equals(customer2))
            System.out.println("test equals method");
    }

    @Test
    public void testOne2One() {
        Manager manager = new Manager();
        manager.setName("Delores");
        Department department = new Department(0L, "HR", manager);
        session.save(manager);
        session.save(department);
    }

    @Test
    public void testOne2OneGet() {
        Department department = session.get(Department.class, 1L);
        System.out.println(department.getDepName());
        System.out.println("**********************");
        System.out.println(department.getManager());
    }

    @Test
    public void testn2n() {
        Category c1 = new Category();
        Category c2 = new Category();
        c1.setCateName("C-AA");
        c2.setCateName("C-BB");

        Item i1 = new Item();
        Item i2 = new Item();
        i1.setItemName("I-AA");
        i2.setItemName("I-BB");

        c1.getItems().add(i1);
        c1.getItems().add(i2);

        c2.getItems().add(i1);
        c2.getItems().add(i2);

        session.save(c1);
        session.save(c2);
        session.save(i1);
        session.save(i2);
    }

    @Test
    public void testHQL() {
        String sql = "FROM Worker w WHERE w.name = :nn AND w.created < :dd ";

        Query query = session.createQuery(sql);
        query.setString("nn", "Delores")
                .setDate("dd", new Date());

        List<Worker> list = query.list();
        for(Worker worker : list) {
            System.out.println(worker);
        }
    }

    @After
    public void after() {
        if (transaction != null)
            transaction.commit();
        if (session != null)
            session.close();
        if (sessionFactory != null)
            sessionFactory.close();
    }

    private void initDB() {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/medusa?characterEncoding=utf8&useSSL=false";
        // Database credentials -- 数据库名和密码自己修改
        final String USER = "root";
        final String PASS = "root";

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql1 = "drop table if exists medusa.worker";
            String sql2 = "create table worker (id int(11) primary key auto_increment, name varchar(20), created datetime)";
            String sql3 = "insert into medusa.worker values (1, 'Delores', now())";
            //PreparedStatement statement = connection.prepareStatement(sql1);
            Statement statement = connection.createStatement();
            statement.execute(sql1);
            statement.execute(sql2);
            statement.execute(sql3);

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
