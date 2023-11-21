package com.elyoub.marjanePromotionApi.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "depart_id", nullable = false)
    private Department department;

    @Column(name = "name", length = 255, nullable = false)
    private String name;




}