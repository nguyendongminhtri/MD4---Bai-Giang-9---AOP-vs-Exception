package service;

import exception.CustomerNameException;
import model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Customer save(Customer customer) throws CustomerNameException;
    Optional<Customer> findById(Long id) throws Exception;
    void deleteById(Long id);
}
