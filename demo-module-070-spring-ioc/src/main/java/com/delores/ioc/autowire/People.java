package com.delores.ioc.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author William
 * @date 3/25/21 10:06 AM
 * @description
 */
@Component
@Controller
@Service
@Repository
@Scope("singleton")
public class People {

    @Value("Delores")
    private String name;

    @Nullable
    @Resource
    private Dog dog;
    @Autowired
    private Cat cat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Dog getDog() {
//        return dog;
//    }
//
//    public void setDog(Dog dog) {
//        this.dog = dog;
//    }

//    public Cat getCat() {
//        return cat;
//    }

//    public void setCat(Cat cat) {
//        this.cat = cat;
//    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", dog=" + dog +
                ", cat=" + cat +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化。。。");
    }
}
