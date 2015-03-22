package com.spike.java.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;

/**
 * Created by Sajith on 3/19/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")

public class TestMessage {

    @Autowired
    QueueSender queueSender;

    @Test
    public void testMessage(){
        queueSender.send("test");
    }
}
