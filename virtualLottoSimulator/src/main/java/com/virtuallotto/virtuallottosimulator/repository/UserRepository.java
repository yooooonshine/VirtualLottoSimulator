package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.User;
import com.virtuallotto.virtuallottosimulator.service.SHA256;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public String save(User user) {
        em.persist(user);
        return user.getId();
    }

    public User findOne(String id) {
        return em.find(User.class, id);
    }

}
