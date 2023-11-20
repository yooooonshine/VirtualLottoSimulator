package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public String save(User user) {
        em.persist(user);
        return user.getId();
    }

    public User find(String id) {
        return em.find(User.class, id);
    }
}
