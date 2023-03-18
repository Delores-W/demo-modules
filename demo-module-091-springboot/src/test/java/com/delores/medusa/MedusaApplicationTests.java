//package com.delores.medusa;
//
//import com.alibaba.fastjson.JSONObject;
//import com.delores.medusa.config.Config;
//import com.delores.medusa.model.Person;
//import com.delores.medusa.model.User;
//import com.delores.medusa.service.RedisService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Transaction;
//
//import java.util.concurrent.TimeUnit;
//
//@SpringBootTest
//class MedusaApplicationTests {
//
//    @Autowired
//    private Person person;
//
//    @Test
//    void contextLoads() {
//        System.out.println(person);
//    }
//
//    @Test
//    void testRedis() {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("hello", "world");
//        jsonObject.put("name", "delores");
//
//        // 开启事务
//        Transaction multi = jedis.multi();
//        String result = jsonObject.toJSONString();
//
//        try {
//            multi.set("user1", result);
//            multi.set("user2", result);
//            multi.exec(); // 执行事务
//        } catch (Exception exception) {
//            multi.discard(); // 放弃事务
//            exception.printStackTrace();
//        } finally {
//            System.out.println(jedis.get("user1"));
//            System.out.println(jedis.get("user2"));
////            {"name":"delores","hello":"world"}
////            {"name":"delores","hello":"world"}
//            jedis.close();
//        }
//
//    }
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private RedisService redisService;
//
//    @Test
//    public void testRedis2() {
//        redisTemplate.opsForValue();
//        redisTemplate.opsForList();
//        redisTemplate.opsForHash();
//        redisTemplate.opsForGeo();
//        redisTemplate.opsForHyperLogLog();
//
//        redisTemplate.opsForValue().set("book", "Old Friends", 20, TimeUnit.SECONDS);
//        System.out.println(redisTemplate.opsForValue().get("book"));
//
//        User user = new User();
//        user.setName("delores");
//        user.setPassword("chinawjm1993");
//
//        // JDK 序列化
//        redisTemplate.opsForValue().set("user", user);
//        System.out.println(redisTemplate.opsForValue().get("user"));
//        // User(id=null, state=0, name=delores, mobile=null, salt=null, password=chinawjm1993, created=null)
//
//
//        redisService.set("boss", user);
//        System.out.println(redisService.get("boss"));
//
//    }
//
//    @Autowired
//    private Config.Jwt jwt;
//
//    @Autowired
//    private Config.Cors cors;
//
//    @Test
//    public void testConfigProperties() {
//        System.out.println(jwt.getAuthenticationPath());
//        System.out.println(cors.getAllowedHeaders());
//        // /auth
//        // [Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, Connection, User-Agent, accessToken]
//    }
//
//    @Test
//    public void testEncoder() {
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("123456");
//        System.out.println(encode);
//        // $2a$10$BTWy2CThMmI5vys/59m7fOILOVb9kLA6dEPsxYCkyOFzx6.shEdD2
//        boolean matches = encoder.matches("123456", encode);
//        System.out.println(matches);
//        // true
//    }
//
//}
