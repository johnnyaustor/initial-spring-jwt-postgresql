package com.jap.initial.springjwt.services;

import com.jap.initial.springjwt.exceptions.AppException;
import com.jap.initial.springjwt.exceptions.EntityExeption;
import com.jap.initial.springjwt.exceptions.ResourceNotFoundException;
import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.payload.ChangePasswordRequest;
import com.jap.initial.springjwt.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EntityManager em;

    @Autowired
    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EntityManager em) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.em = em;
    }

    public Users saveUser(Users newUsers) {
        try {
            if (newUsers.getId() == null) {
                newUsers.setPassword(bCryptPasswordEncoder.encode(newUsers.getPassword()));
            } else {
                Users oldUsers = findById(newUsers.getId());
                newUsers.setPassword(oldUsers.getPassword());
                newUsers.setCreateAt(oldUsers.getCreateAt());
            }

            return usersRepository.save(newUsers);
        } catch (Exception ex) {
            throw new EntityExeption("Cannot Create user: " + ex.getMessage());
        }
    }

    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Users users = usersRepository.findByEmail(changePasswordRequest.getEmail());
        if (users == null) throw new ResourceNotFoundException("Users", "email", changePasswordRequest.getEmail());

        if (!bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(), users.getPassword()))
            throw new EntityExeption("Old Password not match");

        try {
            users.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
            return usersRepository.save(users) != null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AppException(ex.getMessage(), ex);
        }
    }

    public void delete(Long id) {
        Users oldUsers = findById(id);
        usersRepository.delete(oldUsers);
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findById(Long id) {
        Users users = usersRepository.getById(id);
        if (users == null)
            throw new ResourceNotFoundException("Users", "id", id);
        return users;
    }

    public List<Users> findAllByCriteria(String criteria) {
        return usersRepository.findByFullNameContainsOrEmailContainsOrPhoneContainsOrPasswordContains(criteria, criteria, criteria, criteria);
    }

    public Page<Users> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public List<Users> findByCriteria(Map<String, String> params) throws IllegalArgumentException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Users> query = cb.createQuery(Users.class);
        final Root<?> user = query.from(Users.class);
        List<Predicate> predicates = new ArrayList<>();

        for (String key: params.keySet()) {
            String value = params.get(key);
            if (value != null) {
                predicates.add(cb.like(cb.lower(user.get(key)), "%" + value.trim().toLowerCase() + "%"));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }


}
