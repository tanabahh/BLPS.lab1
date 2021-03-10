package blp.lab1.service;

import blp.lab1.model.Order;
import blp.lab1.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void paidForOrder(Long order_id) {
        orderRepository.paidForOrder(order_id);
    }

    public Order fetchOrderById(Long id) {
        return orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null;
    }

    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }
}
