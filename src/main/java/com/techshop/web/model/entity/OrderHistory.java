package com.techshop.web.model.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="order_history")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Embedded
    @Type(type="json")
    @Column(columnDefinition = "json")
    private List<OrderProduct> orderProducts;

}
