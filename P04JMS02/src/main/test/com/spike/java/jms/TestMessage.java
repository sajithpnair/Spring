package com.spike.java.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;

/**
 * Created by Sajith on 3/19/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")

public class TestMessage {

    @Test
    public void testMessage(){
        SimpleMessageProducer producer = new SimpleMessageProducer();
        try {
            producer.sendMessages();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
