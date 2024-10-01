package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Order;

import java.util.List;
import java.util.Optional;


public interface CourierService {
    List<Courier> getAllCourier();

    Courier createCourier(Courier courier);
    Optional<Courier> getCourierById(Long id);

    Optional<Courier> updateCourier(Long id, Courier courier);

    void deleteCourier(Long id);

    List<Courier> findCouriersByName(String username);

    void assignOrderToCourier(Long courierId, Long orderId);

   List<Order> getOrdersByCourier(Long courierId);

    List<Courier> getAvailableCouriers();
}
