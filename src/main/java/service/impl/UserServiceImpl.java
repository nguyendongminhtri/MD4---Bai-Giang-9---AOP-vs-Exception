package service.impl;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IUserRepository;
import service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
