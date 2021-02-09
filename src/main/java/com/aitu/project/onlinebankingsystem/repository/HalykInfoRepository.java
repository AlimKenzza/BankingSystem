package com.aitu.project.onlinebankingsystem.repository;

import com.aitu.project.onlinebankingsystem.model.HalykBank;
import com.aitu.project.onlinebankingsystem.model.HalykTransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HalykInfoRepository extends JpaRepository<HalykTransactionInfo, Integer> {
}
