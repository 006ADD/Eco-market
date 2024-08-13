package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.Courier;
import kg.example.spring.ecomarket.entities.Order;
import kg.example.spring.ecomarket.exception.NotFoundException;
import kg.example.spring.ecomarket.repositories.CourierRepository;
import kg.example.spring.ecomarket.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    public List<Courier> getAllCourier(){
        return courierRepository.findAll();
    }

    // Создание нового курьера
    public Courier createCourier(Courier courier){
        return courierRepository.save(courier);
    }

    // Получение информации о курьере по его ID
    public Optional<Courier> getCourierById(Long id){
        return courierRepository.findById(id);
    }

    // Обновление информации о курьере
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

    // Удаление курьера по его ID
    public void deleteCourier(Long id){
        courierRepository.deleteById(id);
    }

    // Поиск курьеров по имени
    public List<Courier> findCouriersByName(String username){
        return courierRepository.findByUsername(username);
    }

    // Присвоение курьеру заказа
    public void assignOrderToCourier(Long courierId, Long orderId){
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new NotFoundException("Courier not found"));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.setCourier(courier);
        orderRepository.save(order);
    }

    // Получение списка всех заказов, назначенных курьеру
   public List<Order> getOrdersByCourier(Long courierId){
        Optional<Courier> courierOptional = courierRepository.findById(courierId);
        if(courierOptional.isPresent()){
            Courier courier = courierOptional.get();
            return courier.getAssignedOrders();
        }
        else throw new NotFoundException("");
   }

    // Получение доступных курьеров (например, тех, которые сейчас не заняты)
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
