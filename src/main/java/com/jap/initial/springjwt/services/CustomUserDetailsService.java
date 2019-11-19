package com.jap.initial.springjwt.services;

import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

    @Transactional
    public Users loadUserById(Long id) {
        Users user = usersRepository.getById(id);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }
}
