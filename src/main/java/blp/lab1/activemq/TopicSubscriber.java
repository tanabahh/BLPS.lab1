package blp.lab1.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSubscriber {

    /**
     * Имя пользователя по умолчанию
     */
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    /**
     * пароль по умолчанию
     */
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * Адрес подключения по умолчанию
     */
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        // Создаем фабрику соединений
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            // Создаем соединение
            Connection connection = connectionFactory.createConnection();
            // Открываем соединение
            connection.start();
            // Создаем сессию без транзакции
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Создать тему
            Topic myTestTopic = session.createTopic("activemq-topic-test1");

            MessageConsumer messageConsumer = session.createConsumer(myTestTopic);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("Потребитель 1 получил сообщение:" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer messageConsumer2 = session.createConsumer(myTestTopic);
            messageConsumer2.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("Потребитель 2 получил сообщение:" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer messageConsumer3 = session.createConsumer(myTestTopic);
            messageConsumer3.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("Потребитель 3 получил сообщение:" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Даем основному потоку спать на 100 секунд, чтобы объект-получатель сообщения мог продолжать жить в течение определенного периода времени, чтобы можно было отслеживать сообщение
            Thread.sleep(100 * 1000);
            // Закрываем ресурсы
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


