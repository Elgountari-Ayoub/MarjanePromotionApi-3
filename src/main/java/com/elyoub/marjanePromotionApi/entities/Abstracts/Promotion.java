package com.elyoub.marjanePromotionApi.entities.Abstracts;


import com.elyoub.marjanePromotionApi.entities.ProxyAdmin;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@MappedSuperclass
public abstract class Promotion {

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "CIN", nullable = false)
    protected ProxyAdmin admin;



    @Column(name = "percentage", precision = 10, scale = 2)
    protected BigDecimal percentage;

    @CreationTimestamp
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @Column(name = "start_at")
    protected LocalDateTime startAt;

    @Column(name = "end_at")
    protected LocalDateTime endAt;
}
