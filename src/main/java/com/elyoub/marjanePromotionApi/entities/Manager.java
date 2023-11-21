package com.elyoub.marjanePromotionApi.entities;

import com.elyoub.marjanePromotionApi.entities.Abstracts.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "managers")
public class Manager extends Person {

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "CIN", nullable = false)
    private ProxyAdmin admin;

}