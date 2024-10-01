package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Delivery;
import kg.example.spring.ecomarket.entities.enums.DeliveryStatus;

import java.util.List;
import java.util.Optional;


public interface DeliveryOrderService {

    Delivery createDelivery(Delivery delivery);

    Optional<Delivery> getDeliveryById(Long id);

    List<Delivery> getAllDelivery();

    List<Delivery> getDeliveriesByDeliveryStatus(DeliveryStatus deliveryStatus);

    Optional<Delivery> updateDelivery(Long id, Delivery delivery);

    Optional<Courier> assignCourierToBeDelivery(Long deliveryId, Long courierId);
    void deleteDelivery(Long id);


    Optional<Delivery> findDeliveriesByOrderId(Long orderId);

}

