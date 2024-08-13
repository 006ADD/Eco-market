package kg.example.spring.ecomarket.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String address;
    private LocalDateTime orderDate; // Дата заказа
    private String status; // Статус заказа
    private BigDecimal totalAmount; // Сумма заказа
    private String paymentMethod; // Способ оплаты
    private String phoneNumber; // Телефон клиента
    private String email; // Электронная почта клиента
    private String notes; // Комментарии или примечания

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Delivery> deliveries;
}
