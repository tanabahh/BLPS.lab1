package blp.lab1.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class MessageService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "myQueue")
    private Destination myQueue;

    public void sendQueueMessage(String messageContent) {
        jmsTemplate.send(myQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage();
                msg.setText(messageContent);
                return msg;
            }
        });
    }
}
