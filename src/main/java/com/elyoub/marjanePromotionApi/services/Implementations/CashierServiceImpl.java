package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.CashierDTO;
import com.elyoub.marjanePromotionApi.entities.Cashier;
import com.elyoub.marjanePromotionApi.repositories.CashierRepository;
import com.elyoub.marjanePromotionApi.repositories.CashierRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.ICashierService;
import com.elyoub.marjanePromotionApi.services.Interfaces.ICashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CashierServiceImpl implements ICashierService {

    private CashierRepository repository;

    @Autowired
    public CashierServiceImpl(CashierRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cashier> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public List<CashierDTO> findAll() {
        return null;
    }

    @Override
    public Optional<Cashier> save(CashierDTO cashierDTO) {
        Cashier cashier = mapToEntity(cashierDTO);
        return Optional.of(repository.save(cashier));
    }

    @Override
    public void delete(String cin) {

    }

    @Override
    public Optional<Cashier> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public CashierDTO mapToDTO(Cashier cashier){
        return CashierDTO.builder()
                .cin(cashier.getCin())
                .admin(cashier.getProxyAdmin())
                .email(cashier.getEmail())
                .password(cashier.getPassword())
                .firstName(cashier.getFirstName())
                .lastName(cashier.getLastName())
                .phone(cashier.getPhone())
                .build();
    }

    public Cashier mapToEntity(CashierDTO cashierDTO){
        Cashier cashier =  new Cashier();
        cashier.setCin(cashierDTO.getCin());
        cashier.setProxyAdmin(cashierDTO.getAdmin());
        cashier.setEmail(cashierDTO.getEmail());
        cashier.setPassword(cashierDTO.getPassword());
        cashier.setFirstName(cashierDTO.getFirstName());
        cashier.setLastName(cashierDTO.getLastName());
        cashier.setPhone(cashierDTO.getPhone());

        return cashier;
    }

    public  boolean isCurrentTimeInRange() {
        final LocalTime START_TIME = LocalTime.of(8, 0); // 8 AM
        final LocalTime END_TIME = LocalTime.of(12, 0); // 12 PM
        LocalTime currentTime = LocalTime.now();
        return !currentTime.isBefore(START_TIME) && currentTime.isBefore(END_TIME);
    }
}
