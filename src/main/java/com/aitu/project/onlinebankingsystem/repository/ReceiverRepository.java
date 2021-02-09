package com.aitu.project.onlinebankingsystem.repository;

import com.aitu.project.onlinebankingsystem.model.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Integer> {
}
