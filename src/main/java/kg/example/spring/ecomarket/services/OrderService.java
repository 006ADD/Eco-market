package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> findOrderById(Long id);
    List<Order> allOrders();
    Order createOrder(Order order); // Создать новый заказ
    Order updateOrder(Long id, Order order); // Обновить существующий заказ
    void deleteOrder(Long id); // Удалить заказ по ID
    List<Order> findOrdersByUserIdService(Long userId); // Найти все заказы конкретного пользователя
    void updateOrderStatus();//Long id, OrderStatus status); // Изменить статус заказа
    double calculateTotal(Long id); // Рассчитать общую стоимость заказа
    void applyDiscount(Long id, double discount); // Применить скидку к заказу;
}
