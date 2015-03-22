package com.spike.java.jms;

import javax.jms.JMSException;

/**
 * Created by Sajith on 3/19/2015.
 */
public class App {
    public static void main(String[] args) {
        SimpleMessageProducer simpleMessageProducer = new SimpleMessageProducer();
        try {
            simpleMessageProducer.sendMessages();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
