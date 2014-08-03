package com.spike.java;

/**
 * Created by sajith
 * Created on 7/6/14.11:49 AM
 */
public class HelloWorld {
    private String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void printHello() {
        System.out.println("Hello " + this.getName());
    }
}
