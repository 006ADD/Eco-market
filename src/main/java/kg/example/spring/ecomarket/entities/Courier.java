package kg.example.spring.ecomarket.entities;

import jakarta.persistence.*;
import kg.example.spring.ecomarket.entities.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courier")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private VehicleType vehicleType;
    private String licensePlate;
    private boolean isBusy;
    @OneToMany
    private List<Order> assignedOrders;
}
