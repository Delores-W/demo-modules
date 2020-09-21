package demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    // 只使用log4j
    private static org.apache.logging.log4j.Logger logger1 = org.apache.logging.log4j.LogManager.getLogger(LogTest.class);

    // 结合slf4j
    private static Logger logger2 = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {

        logger1.info("Cool!!! This is a info logs message.");

        logger1.warn("Cool!!! This is a warning logs message.");

        logger1.error("Cool!!! This is a error logs message.");

        logger1.error("Oops error!!!", new NullPointerException("NullError"));


        System.out.println("--------------------------------------------");


        logger2.info("Cool!!! This is a info logs message.");

        logger2.warn("Cool!!! This is a warning logs message.");

        logger2.error("Cool!!! This is a error logs message.");

        logger2.error("Oops error again!!!", new NullPointerException("NullErrorAgain"));
    }
}
