package com.virtuallotto.virtuallottosimulator.repository;

import com.virtuallotto.virtuallottosimulator.domain.Order;
import com.virtuallotto.virtuallottosimulator.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
