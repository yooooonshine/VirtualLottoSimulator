package com.virtuallotto.virtuallottosimulator.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BuyLottoTicketsRepository {

    @PersistenceContext
    EntityManager em;
}
