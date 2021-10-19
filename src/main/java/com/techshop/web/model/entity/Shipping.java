package com.techshop.web.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;
    private LocalDateTime received;
    @Column(name = "id_address")
    private Integer idAddress;
    @Column(name="id_order_history")
    private Integer idOrderHistory;

}
