package com.spike.java.jms;

/**
 * Created by Sajith on 3/21/2015.
 */

import org.springframework.stereotype.Component;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

@Component
public class JmsExceptionListener implements ExceptionListener {
    public void onException(final JMSException e) {
        e.printStackTrace();
    }
}
