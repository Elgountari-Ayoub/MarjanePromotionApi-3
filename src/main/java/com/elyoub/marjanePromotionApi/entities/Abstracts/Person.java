package com.elyoub.marjanePromotionApi.entities.Abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Person {
    @Column(name = "firstName", length = 255, nullable = false)
    protected String firstName;

    @Column(name = "lastName", length = 255, nullable = false)
    protected String lastName;

    @Column(name = "email", length = 255, nullable = false)
    protected String email;

    @Column(name = "password", length = 255, nullable = false)
    protected String password;

    @Column(name = "phone", length = 255, nullable = false)
    protected String phone;
}
