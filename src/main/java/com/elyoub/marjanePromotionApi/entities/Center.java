package com.elyoub.marjanePromotionApi.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "centers")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private ProxyAdmin admin;

/*

    @ManyToMany(mappedBy = "centers")
    private List<ProductPromotion> productPromotions;

    @OneToMany(mappedBy = "center")
    private List<PromotionCenter> centerPromotions = new ArrayList<>();

 */


}