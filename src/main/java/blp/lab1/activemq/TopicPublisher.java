package blp.lab1.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPublisher {

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
            // Создаем тему, используемую как сообщение о подписке потребителя
            Topic myTestTopic = session.createTopic("activemq-topic-test1");
            // Производитель сообщения
            MessageProducer producer = session.createProducer(myTestTopic);

            for (int i = 1; i <= 3; i++) {
                TextMessage message = session.createTextMessage("отправлять сообщения" + i);
                producer.send(myTestTopic, message);
            }

            // Закрываем ресурсы
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


