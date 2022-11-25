package Practic_3;
import javax.naming.InitialContext;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSession;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class Receiver {
    public static void main(String[] args) throws Exception
    {
        InitialContext context = new InitialContext();

        Queue queue = (Queue) context.lookup("queue/queue0");
        QueueConnectionFactory conFactory = (QueueConnectionFactory) context.lookup ("queue/connectionFactory");

        QueueConnection queConn = conFactory.createQueueConnection();
        QueueSession queSession = queConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        QueueReceiver queReceiver = queSession.createReceiver(queue);

        queConn.start();
        TextMessage message = (TextMessage) queReceiver.receive();
        System.out.println("Message Received: " + message.getText());
        queConn.close();
    }
}
