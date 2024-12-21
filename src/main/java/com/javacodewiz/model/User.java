package com.javacodewiz.model;


import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.JdbcTypeCode;

import org.hibernate.type.SqlTypes;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users-save")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @JdbcTypeCode(SqlTypes.JSON)
    private Address address;

}
