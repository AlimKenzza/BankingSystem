package com.aitu.project.onlinebankingsystem.repository;

import com.aitu.project.onlinebankingsystem.model.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionInfoRepository  extends JpaRepository<TransactionInfo, Integer> {
}
