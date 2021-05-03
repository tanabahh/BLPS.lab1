package blp.lab1.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage txtMsg = (TextMessage) message;
                String messageStr = txtMsg.getText();
                System.out.println("Слушатель очереди получил текстовое сообщение:" + messageStr);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Поддерживает только сообщения типа TextMessage!");
        }
    }
}
