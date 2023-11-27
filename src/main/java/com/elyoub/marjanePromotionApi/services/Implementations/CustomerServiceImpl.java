package com.elyoub.marjanePromotionApi.services.Implementations;

import com.elyoub.marjanePromotionApi.dtos.CustomerDTO;
import com.elyoub.marjanePromotionApi.entities.Customer;
import com.elyoub.marjanePromotionApi.helpers.EmailService;
import com.elyoub.marjanePromotionApi.observer.IObserver;
import com.elyoub.marjanePromotionApi.repositories.CustomerRepository;
import com.elyoub.marjanePromotionApi.services.Interfaces.ICustomerService;
import com.elyoub.marjanePromotionApi.services.Interfaces.ICustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository repository;
    private final EmailService emailService;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public Optional<Customer> findByCIN(String cin) {
        return repository.findById(cin);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = repository.findAll();
        // List<AdminRayonDTO> adminRayonDTOS = adminsRayonList.stream()
        return customers.stream().map(adminRayon ->
                modelMapper.map(adminRayon,CustomerDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> save(CustomerDTO customerDTO) {
        Customer customer = mapToEntity(customerDTO);
        return Optional.of(repository.save(customer));
    }

    @Override
    public void delete(String cin) {

    }

    @Override
    public Optional<Customer> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public CustomerDTO mapToDTO(Customer customer) {
        return CustomerDTO.builder()
                .cin(customer.getCin())
                .admin(customer.getAdmin())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .build();
    }

    public Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCin(customerDTO.getCin());
        customer.setAdmin(customerDTO.getAdmin());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());

        return customer;
    }

    public boolean isCurrentTimeInRange() {
        final LocalTime START_TIME = LocalTime.of(8, 0); // 8 AM
        final LocalTime END_TIME = LocalTime.of(12, 0); // 12 PM
        LocalTime currentTime = LocalTime.now();
        return !currentTime.isBefore(START_TIME) && currentTime.isBefore(END_TIME);
    }
}
