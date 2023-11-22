package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LottoRepository {

    private final EntityManager em;

    public void save(Lotto lotto) {
        em.persist(lotto);
    }

    public Lotto findOne(Long id) {
        return em.find(Lotto.class, id);
    }

    public List<Lotto> findAll() {
        return em.createQuery("select l from Lotto l", Lotto.class)
                .getResultList();
    }
}
