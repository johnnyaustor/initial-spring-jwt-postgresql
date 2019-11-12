package com.jap.initial.springjwt.services;

import com.jap.initial.springjwt.exceptions.EntityExeption;
import com.jap.initial.springjwt.model.Users;
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

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EntityManager em;

    public Iterable<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users saveUser(Users newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            return usersRepository.save(newUser);
        } catch (Exception ex) {
            throw new EntityExeption("Cannot Create user: " + ex.getMessage());
        }
    }

    public List<?> findByCriteria(Map<String, String> params) throws IllegalArgumentException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<?> query = cb.createQuery(Users.class);
        final Root<?> user = query.from(Users.class);
        List<Predicate> predicates = new ArrayList<>();

        for (String key: params.keySet()) {
            String value = params.get(key);
            if (value != null || !value.isEmpty()) {
                predicates.add(cb.like(cb.lower(user.get(key)), "%" + value.trim().toLowerCase() + "%"));
            }
        }

        query.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(query).getResultList();
    }

    public Users findById(Long id) {
        return usersRepository.getById(id);
    }

    public List<Users> findAllByCriteria(String criteria) {
        return usersRepository.findByFullNameContainsOrEmailContainsOrPhoneContainsOrPasswordContains(criteria, criteria, criteria, criteria);
    }

    public Page<Users> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }
}
