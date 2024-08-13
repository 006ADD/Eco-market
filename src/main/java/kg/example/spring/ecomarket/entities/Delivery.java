package kg.example.spring.ecomarket.entities;

import jakarta.persistence.*;
import kg.example.spring.ecomarket.entities.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private DeliveryStatus status;
    private String trackingNumber;
    private LocalDateTime deliveryDate; // Дата доставки
    private String courierName; // Имя курьера
    private String deliveryMethod; // Способ доставки
    private String deliveryAddress; // Адрес доставки
    private String comments; // Комментарии
    private LocalTime estimatedDeliveryTime; // Предполагаемое время доставки
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;
}
