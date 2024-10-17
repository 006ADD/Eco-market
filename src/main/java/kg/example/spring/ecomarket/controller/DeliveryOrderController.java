package kg.example.spring.ecomarket.controller;
import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Delivery;
import kg.example.spring.ecomarket.entities.enums.DeliveryStatus;
import kg.example.spring.ecomarket.services.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryService;
    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(id);
        return delivery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDelivery() {
        List<Delivery> deliveries = deliveryService.getAllDelivery();
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Delivery>> getDeliveriesByDeliveryStatus(@PathVariable DeliveryStatus status) {
        List<Delivery> deliveries = deliveryService.getDeliveriesByDeliveryStatus(status);
        return ResponseEntity.ok(deliveries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        Optional<Delivery> updatedDelivery = deliveryService.updateDelivery(id, delivery);
        return updatedDelivery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{deliveryId}/assign-courier/{courierId}")
    public ResponseEntity<Courier> assignCourierToDelivery(@PathVariable Long deliveryId, @PathVariable Long courierId) {
        Optional<Courier> assignedCourier = deliveryService.assignCourierToBeDelivery(deliveryId, courierId);
        return assignedCourier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Delivery> findDeliveriesByOrderId(@PathVariable Long orderId) {
        Optional<Delivery> delivery = deliveryService.findDeliveriesByOrderId(orderId);
        return delivery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
