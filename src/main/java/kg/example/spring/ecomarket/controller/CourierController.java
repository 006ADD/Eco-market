package kg.example.spring.ecomarket.controller;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/allCourier")
    public ResponseEntity<List<Courier>> getAllСouriers(){
        List<Courier> get = courierService.getAllCourier();
        return ResponseEntity.ok(get);
    }
}
