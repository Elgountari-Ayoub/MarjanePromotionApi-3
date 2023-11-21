package com.elyoub.marjanePromotionApi.entities;

import com.elyoub.marjanePromotionApi.entities.Abstracts.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "proxiesAdmin")
public class ProxyAdmin  extends Person{

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "superAdmin_id", referencedColumnName = "CIN", nullable = false)
    private SuperAdmin superAdmin;

}