package edu.clothify.entity;

import edu.clothify.util.converter.OrderStatusConverter;
import edu.clothify.util.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "Address" , nullable = false)
    private String address;

    @Column(name ="Contact" , nullable = false)
    private String phone;

    @Column(name ="Tax" , nullable = false)
    private BigInteger tax;

    @Column(name ="Delivery Charge" , nullable = false)
    private String charge;

    @Column(name = "Land Code" , nullable = false)
    private Integer zipCode;

    @Column(name = "Order Total" , nullable = false)
    private Long tot;

    @Column(name = "City" , nullable = false)
    private String city;

    @OneToOne(mappedBy = "orders")
    private BillingInfo billingInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "orders")
    private Payment payment;

    @Column(name = "Delivery Status" , nullable = false)
    @Convert(converter =  OrderStatusConverter.class)
    private OrderStatus status;


}