package com.spike.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/Spring-Module.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloSetterBean");
        obj.printHello();
    }
}
