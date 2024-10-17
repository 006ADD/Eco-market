package kg.example.spring.ecomarket.controller;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/allCourier")
    public ResponseEntity<List<Courier>> getAllСouriers(){
        List<Courier> get = courierService.getAllCourier();
        return ResponseEntity.ok(get);
    }

    @PostMapping("/new/coutier")
    public ResponseEntity<Courier> createNewCourier(@RequestBody Courier courier){
        return ResponseEntity.status(HttpStatus.CREATED).body(courierService.createCourier(courier));
    }

    @GetMapping("/get/courier/{courierId}")
    public ResponseEntity<Optional<Courier>> getCourierId(@PathVariable Long courierId){
        Optional<Courier> getId = courierService.getCourierById(courierId);
        return ResponseEntity.ok(getId);
    }

    @PostMapping("/update/courier")
    public ResponseEntity<Courier> updateCourier(@PathVariable Long courierId, @RequestBody Courier courier){
        Optional<Courier> update = courierService.updateCourier(courierId, courier);
        return update.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/assing/{courierId}/{orderId}")
    public ResponseEntity<?> assignOrderToCourier(@PathVariable Long courierId, @PathVariable Long orderId){
        courierService.assignOrderToCourier(courierId, orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/orderByCourier/{courierId}")
    public ResponseEntity<List<Order>> getOrdersByCourier(@PathVariable Long courierId){
        return ResponseEntity.ok(courierService.getOrdersByCourier(courierId));
    }

    @GetMapping("/get/allAvailableCouriers/")
    public ResponseEntity<List<Courier>> getAvailableCouriers(){
        return ResponseEntity.ok(courierService.getAvailableCouriers());
    }

    @GetMapping("/find/courier/{name}")
    public ResponseEntity<List<Courier>> findCourierByName(@PathVariable String name){
        return ResponseEntity.ok(courierService.findCouriersByName(name));
    }


    @DeleteMapping("/delete/courier/{id}")
    public void deleteCourierId(@PathVariable Long id){
        courierService.deleteCourier(id);
    }
}
