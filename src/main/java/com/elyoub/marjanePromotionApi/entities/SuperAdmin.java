package com.elyoub.marjanePromotionApi.entities;

import com.elyoub.marjanePromotionApi.entities.Abstracts.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "superAdmin")
public class SuperAdmin extends Person {

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

}