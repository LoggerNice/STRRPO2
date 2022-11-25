package Practic_3;
import javax.naming.InitialContext;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSender;
import javax.jms.DeliveryMode;
import javax.jms.QueueSession;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class Sender {
    public static void main(String[] args) throws Exception
    {
        InitialContext context = new InitialContext();

        Queue queue = (Queue) context.lookup("queue/queue0");
        QueueConnectionFactory conFactory = (QueueConnectionFactory) context.lookup ("queue/connectionFactory");

        QueueConnection queConn = conFactory.createQueueConnection();
        QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
        QueueSender queSender = queSession.createSender(queue);
        queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        TextMessage message = queSession.createTextMessage("It is model MOM (JMS)");

        queSender.send(message);
        System.out.println("Message Sent: " + message.getText());
        queConn.close();
    }
}
