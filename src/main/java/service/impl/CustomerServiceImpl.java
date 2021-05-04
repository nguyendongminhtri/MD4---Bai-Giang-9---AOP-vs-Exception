package service.impl;

import exception.CustomerNameException;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import repository.ICustomerRepository;
import service.ICustomerService;

import java.util.Optional;
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) throws CustomerNameException {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e){
            throw new CustomerNameException();
        }
    }

    @Override
    public Optional<Customer> findById(Long id) throws Exception{
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(!customerOptional.isPresent()){
            throw new Exception("Not Found Customer!!!");
        }
        return customerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
