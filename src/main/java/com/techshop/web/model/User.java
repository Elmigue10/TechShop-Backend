package com.techshop.web.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
