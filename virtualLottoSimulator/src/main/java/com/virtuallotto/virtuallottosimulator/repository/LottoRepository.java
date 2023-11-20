package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.Lotto;
import com.virtuallotto.virtuallottosimulator.domain.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {
}
