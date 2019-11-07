package com.jap.initial.springjwt.services;

import com.jap.initial.springjwt.exceptions.UserCreateException;
import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Iterable<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users saveUser(Users newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return usersRepository.save(newUser);
        } catch (Exception ex) {
            throw new UserCreateException("Cannot Create user: " + ex.getMessage());
        }
    }
}
