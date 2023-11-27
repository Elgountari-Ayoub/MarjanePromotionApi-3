package com.elyoub.marjanePromotionApi.observer;
import com.elyoub.marjanePromotionApi.services.Implementations.ManagerServiceImpl;

// observable subject
public interface IObservable {
    // Subscribe
    public void addManager(ManagerServiceImpl managerService);
    //UnSubscribe
    public void removeManager(ManagerServiceImpl managerService);

    public void notifyManager();


}
