package kg.example.spring.ecomarket.repositories;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Delivery;
import kg.example.spring.ecomarket.entities.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    Optional<List<Delivery>> findDeliveriesByDeliveryStatus(DeliveryStatus deliveryStatus);

    Optional<Courier> assignCourierToBeDelivery(Long deliveryId,Long courierId);
    List<Delivery> getDeliveriesByCourier();
    void findDeliveriesByDateRange();
    Optional<Delivery> findDeliveriesByOrderId(Long orderId);
}
