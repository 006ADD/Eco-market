package kg.example.spring.ecomarket.services.impls;

import kg.example.spring.ecomarket.entities.Category;
import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.exception.NotFoundException;
import kg.example.spring.ecomarket.repositories.CourierRepository;
import kg.example.spring.ecomarket.repositories.OrderRepository;
import kg.example.spring.ecomarket.services.CategoryService;
import kg.example.spring.ecomarket.services.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultCourierService implements CourierService {

    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Courier> getAllCourier(){
        return courierRepository.findAll();
    }

    @Override
    public Courier createCourier(Courier courier){
        return courierRepository.save(courier);
    }

    @Override
    public Optional<Courier> getCourierById(Long id){
        return courierRepository.findById(id);
    }

    @Override
    public Optional<Courier> updateCourier(Long id, Courier courier){
        Optional<Courier> findId = courierRepository.findById(id);
        if(findId.isPresent()){
            Courier existing = findId.get();
            existing.setName(courier.getName());
            existing.setLastName(courier.getLastName());
            existing.setPhoneNumber(courier.getPhoneNumber());
            existing.setEmail(courier.getEmail());
            existing.setVehicleType(courier.getVehicleType());
            existing.setLicensePlate(courier.getLicensePlate());
            return Optional.of(courierRepository.save(existing));
        }
        return Optional.empty();
    }

    @Override
    public void deleteCourier(Long id){
        courierRepository.deleteById(id);
    }

    @Override
    public List<Courier> findCouriersByName(String username){
        return courierRepository.findByUsername(username);
    }

    @Override
    public void assignOrderToCourier(Long courierId, Long orderId){
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new NotFoundException("Courier not found"));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.setCourier(courier);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCourier(Long courierId){
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if(courierOptional.isPresent()){
            Courier courier = courierOptional.get();
            return courier.getAssignedOrders();
        }
        else throw new NotFoundException("");
    }

    @Override
    public List<Courier> getAvailableCouriers(){
        List<Courier> availableCouriers = new ArrayList<>();
        List<Courier> allCouriers = courierRepository.findAll();
        for(Courier courier : allCouriers){
            if(!courier.isBusy()){
                availableCouriers.add(courier);
            }
        }
        return availableCouriers;
    }

}
