package com.aitu.project.onlinebankingsystem.repository;

import com.aitu.project.onlinebankingsystem.model.CustomerAcc;
import com.aitu.project.onlinebankingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerAcc, Integer> {
//    @Query("SELECT u FROM CustomerAcc u WHERE u.cardId = :cardId")
//    CustomerAcc getCustomerAccByCardId(@Param("cardId") String cardId);
}
