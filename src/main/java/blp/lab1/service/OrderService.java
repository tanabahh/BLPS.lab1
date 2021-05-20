package blp.lab1.service;

import blp.lab1.ActiveMQConfig;
import blp.lab1.model.Notification;
import blp.lab1.model.Order;
import blp.lab1.model.Status;
import blp.lab1.repository.NotificationRepository;
import blp.lab1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final TransactionTemplate transactionTemplate;
    private final ApplicationContext applicationContext;

    @Autowired
    public OrderService(OrderRepository orderRepository, NotificationRepository notificationRepository,
                        PlatformTransactionManager transactionManager, ApplicationContext applicationContext) {
        this.orderRepository = orderRepository;
        this.notificationRepository = notificationRepository;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.applicationContext = applicationContext;
    }

    public void pay(Long orderId) {
        //возможно еще подумать над транзакциями и реализацией
        Order order = orderRepository.findById(orderId).get();
        changeStatus(orderId, Status.PAID);
        applicationContext.getBean(ActiveMQConfig.MyGateway.class).
                sendToMqtt(String.format("Заказ оплачен: orderId = %d, userId = %d", orderId, order.getUser().getId()));
    }

    public void changeStatus(Long order_id, Status new_status) {
        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                Order order = orderRepository.findById(order_id).get();
                orderRepository.changeStatus(order_id, new_status.toString());
                notificationRepository.save(new Notification(order, order.getUser(), LocalDate.now()));
                //int a = 2/0;
                notificationRepository.save(new Notification(order, order.getUser(), LocalDate.now()));
            }
        });
    }

    public Order fetchOrderById(Long id) {
        return orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null;
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }
}
