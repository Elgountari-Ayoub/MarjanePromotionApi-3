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
@Table(name = "Cashier")
public class Cashier extends Person{

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "proxy_admin_cin", referencedColumnName = "CIN", nullable = false)
    private ProxyAdmin proxyAdmin;

}