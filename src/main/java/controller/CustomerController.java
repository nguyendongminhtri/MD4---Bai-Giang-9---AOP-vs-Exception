package controller;

import exception.CustomerNameException;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.impl.CustomerServiceImpl;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;
    @GetMapping("/")
    public ModelAndView listCustomer(){
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("listCustomer",customers);
        return modelAndView;
    }
    @GetMapping("/customer/{id}")
    public ModelAndView detailCustomer(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/customer/detail");
            modelAndView.addObject("detailCustomer",customer);
            return modelAndView;
        } catch (Exception e){
            return new ModelAndView("/customer/error");
        }
    }
    @GetMapping("/create-customer")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/customer/add");
        modelAndView.addObject("createCustomerForm",new Customer());
        return modelAndView;
    }
    @PostMapping("/create/customer")
    public ModelAndView createCustomer(@ModelAttribute("createCustomerForm") Customer customer){
        try {
            customerService.save(customer);
            return new ModelAndView("redirect:/");
        } catch (CustomerNameException c) {
            return new ModelAndView("/customer/nameError");
        }
    }
}
