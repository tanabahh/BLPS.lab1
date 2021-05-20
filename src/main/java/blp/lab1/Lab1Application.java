package blp.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@SpringBootApplication
public class Lab1Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JtaTransactionManager();
    }

//    @Bean
//    public PooledConnectionFactory jmsFactory() {
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616"); //изменить надо будет на xmpp://localhost:61222
//        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
//        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
//        pooledConnectionFactory.setMaxConnections(100);
//        return pooledConnectionFactory;
//    }
//
//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setTargetConnectionFactory(jmsFactory());
//        connectionFactory.setSessionCacheSize(1);
//        return connectionFactory;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(cachingConnectionFactory());
//        template.setMessageConverter(new SimpleMessageConverter());
//        return template;
//    }
//
//    @Bean
//    public ActiveMQQueue myQueue() {
//        return new ActiveMQQueue("spring-queue");
//    }
//
//    @Bean
//    public QueueListener queueListener() {
//        return new QueueListener();
//    }
//
//    @Bean
//    public DefaultMessageListenerContainer queueContainer() {
//        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
//        defaultMessageListenerContainer.setConnectionFactory(cachingConnectionFactory());
//        defaultMessageListenerContainer.setDestination(myQueue());
//        defaultMessageListenerContainer.setMessageListener(queueListener()); //вот тут еще один бин
//        return defaultMessageListenerContainer;
//    }
//
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
