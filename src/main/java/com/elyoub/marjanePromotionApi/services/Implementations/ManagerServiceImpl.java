package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.ManagerDTO;
import com.elyoub.marjanePromotionApi.entities.Manager;
import com.elyoub.marjanePromotionApi.helpers.EmailService;
import com.elyoub.marjanePromotionApi.observer.IObserver;
import com.elyoub.marjanePromotionApi.repositories.ManagerRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.IManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements IManagerService, IObserver {

    private ManagerRepository repository;
    private final EmailService emailService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ManagerServiceImpl(ManagerRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public Optional<Manager> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public List<ManagerDTO> findAll() {
        List<Manager> managers = repository.findAll();
        // List<AdminRayonDTO> adminRayonDTOS = adminsRayonList.stream()
        return managers.stream().map(adminRayon ->
                modelMapper.map(adminRayon,ManagerDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Manager> save(ManagerDTO managerDTO) {
        Manager manager = mapToEntity(managerDTO);
        return Optional.of(repository.save(manager));
    }

    @Override
    public void delete(String cin) {

    }

    @Override
    public Optional<Manager> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public ManagerDTO mapToDTO(Manager manager) {
        return ManagerDTO.builder()
                .cin(manager.getCin())
                .admin(manager.getAdmin())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .phone(manager.getPhone())
                .build();
    }

    public Manager mapToEntity(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setCin(managerDTO.getCin());
        manager.setAdmin(managerDTO.getAdmin());
        manager.setEmail(managerDTO.getEmail());
        manager.setPassword(managerDTO.getPassword());
        manager.setFirstName(managerDTO.getFirstName());
        manager.setLastName(managerDTO.getLastName());
        manager.setPhone(managerDTO.getPhone());

        return manager;
    }

    public boolean isCurrentTimeInRange() {
        final LocalTime START_TIME = LocalTime.of(8, 0); // 8 AM
        final LocalTime END_TIME = LocalTime.of(12, 0); // 12 PM
        LocalTime currentTime = LocalTime.now();
        return !currentTime.isBefore(START_TIME) && currentTime.isBefore(END_TIME);
    }

    @Override
    public void update(ManagerDTO managerDTO) {
        emailService.sendSimpleMessage(managerDTO.getEmail(), "New Promotion", "check new promotion");
        System.out.println("welcome " + managerDTO.getAdmin() + "check another promotion");
    }
}
