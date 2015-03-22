package com.spike.java.jms;

/**
 * Created by Sajith on 3/19/2015.
 */

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.log4j.Logger;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoadActiveMQ extends HttpServlet {

    /**
     * serialVersionUID for serialization
     */
    private static final long serialVersionUID = -8289081958495740549L;

    private BrokerService broker;

    private static final Logger logger = Logger.getLogger(LoadActiveMQ.class
            .getName());

    @Override
    public void init() throws ServletException {
// / Automatically java script can run here
        logger.info("Load activeMQ");
// configure the broker
        try {
            broker = new BrokerService();
            broker.addConnector("vm://localhost:61616");
            broker.start();
            logger.info("ActiveMQ loaded succesfully");

            thread(new HelloWorldProducer(), false);
            thread(new HelloWorldConsumer(), false);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to load ActiveMQ!");
        }
    }

    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public void destroy() {
        try {
            logger.info("ActiveMQ exiting");
            broker.stop();
            logger.info("ActiveMQ exit succesfully");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to exit ActiveMQ!");
        }
    }

    public static class HelloWorldConsumer implements Runnable, ExceptionListener {
        public void run() {
            try {

                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost:61616");

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();

                connection.setExceptionListener(this);

                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageConsumer from the Session to the Topic or Queue
                MessageConsumer consumer = session.createConsumer(destination);

                for (int i = 0; i < 50; i++) {

                    // Wait for a message
                    Message message = consumer.receive(1000);

                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        System.out.println("Received: " + text);
                    } else {
                        System.out.println("Received: " + message);
                    }

                }
                consumer.close();
                session.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }

        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }
    }

    public static class HelloWorldProducer implements Runnable {
        public void run() {
            try {
                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost:61616");

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();

                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("TEST.FOO");

                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                for (int i = 0; i < 50; i++) {
                    // Create a messages
                    String text = "Hello world!" + i;
                    TextMessage message = session.createTextMessage(text);

                    // Tell the producer to send the message
                    System.out.println("Hello world!" + i);
                    producer.send(message);

                }
                // Clean up
                session.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
    }
}