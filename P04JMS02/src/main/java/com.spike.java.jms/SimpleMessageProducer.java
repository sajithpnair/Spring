package com.spike.java.jms;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Date;

/**
 * Created by Sajith on 3/19/2015.
 */

public class SimpleMessageProducer {

    private static final Logger LOG = Logger.getLogger(SimpleMessageProducer.class);

    @Resource
    protected JmsTemplate producerTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.producerTemplate = jmsTemplate;
    }

    protected int numberOfMessages = 100;


    public void sendMessages() throws JMSException {

        StringBuilder payload = null;


        for (int i = 0; i < numberOfMessages; ++i) {


            payload = new StringBuilder();

            payload.append("Message [").append(i).append("] sent at: ").append(new Date());


            final StringBuilder finalPayload = payload;
            producerTemplate.send(new MessageCreator() {

                public Message createMessage(Session session) throws JMSException {

                    TextMessage message = session.createTextMessage(finalPayload.toString());

//                    message.setIntProperty("messageCount", i);
//
//                    LOG.info("Sending message number [" + i + "]");

                    return message;

                }

            });

        }

    }

}

