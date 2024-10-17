package kg.example.spring.ecomarket.controller;
import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Order>> allOrders() {
        List<Order> orders = orderService.allOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> findOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findOrdersByUserIdService(userId);
        return ResponseEntity.ok(orders);
    }

//    @PutMapping("/{id}/status")
//    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
//        orderService.updateOrderStatus(id, status);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> calculateTotal(@PathVariable Long id) {
        double total = orderService.calculateTotal(id);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/{id}/discount")
    public ResponseEntity<Void> applyDiscount(@PathVariable Long id, @RequestParam double discount) {
        orderService.applyDiscount(id, discount);
        return ResponseEntity.noContent().build();
    }
}
