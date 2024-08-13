package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Delivery;
import kg.example.spring.ecomarket.repositories.CourierRepository;
import kg.example.spring.ecomarket.repositories.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryOrder {
    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;

    public Delivery createDelivery(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public Optional<Delivery> getDeliveryById(Long id){
        return deliveryRepository.findById(id);
    }

    public List<Delivery> getAllDelivery(){
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> updateDelivery(Long id, Delivery delivery){
        return deliveryRepository.findById(id).map(existing -> {
            existing.setStatus(delivery.getStatus());
            existing.setTrackingNumber(delivery.getTrackingNumber());
            existing.setDeliveryDate(delivery.getDeliveryDate());
            existing.setCourierName(delivery.getCourierName());
            existing.setDeliveryMethod(delivery.getDeliveryMethod());
            existing.setDeliveryAddress(delivery.getDeliveryAddress());
            existing.setComments(delivery.getComments());
            existing.setEstimatedDeliveryTime(delivery.getEstimatedDeliveryTime());
            return Optional.of(deliveryRepository.save(existing));
        }).orElse(Optional.empty());
    }

    public Optional<Courier> assignCourierToBeDelivery(Long deliveryId, Long courierId){
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryId);
        Optional<Courier> courierOptional = courierRepository.findById(courierId);

        if(deliveryOptional.isPresent() && courierOptional.isPresent()){
            Delivery delivery = deliveryOptional.get();
            Courier courier = courierOptional.get();

            delivery.setCourier(courier);
            deliveryRepository.save(delivery);
            return Optional.of(courier);
        }

        return Optional.empty();
    }
    public void deleteDelivery(Long id){
        deliveryRepository.deleteById(id);
    }


    public Optional<Delivery> findDeliveriesByOrderId(Long orderId){
        return deliveryRepository.findDeliveriesByOrderId(orderId);
    }

}
