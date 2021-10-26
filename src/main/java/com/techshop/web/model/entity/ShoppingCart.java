package com.techshop.web.model.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@ToString
@RequiredArgsConstructor
@Getter
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="id_product")
    private Integer idProduct;
    @Column(name = "id_user")
    private Integer idUser;
    private Integer quatity;
    @Column(name = "total_price")
    private Integer totalPrice;

    public ShoppingCart(Integer idUser,Integer idProduct, Integer quatity) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.quatity = quatity;
    }
}