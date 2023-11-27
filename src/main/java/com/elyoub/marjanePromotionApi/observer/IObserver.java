package com.elyoub.marjanePromotionApi.observer;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;

public interface IObserver {

    // On Success , On Action
    public void update(ManagerDTO managerDTO);
}