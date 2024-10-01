package kg.example.spring.ecomarket.services.impls;

import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.repositories.OrderRepository;
import kg.example.spring.ecomarket.repositories.impl.InMemoryOrderRepository;
import kg.example.spring.ecomarket.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        if(orderRepository.findById(id)==null)
            return null;
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findOrdersByUserIdService(Long userId) {
        return null;
    }

    @Override
    public void updateOrderStatus() {

    }

    @Override
    public double calculateTotal(Long id) {
        return 0;
    }

    @Override
    public void applyDiscount(Long id, double discount) {

    }
}
