package com.aitu.project.onlinebankingsystem.repository;

import com.aitu.project.onlinebankingsystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);
    User findByUsername(String username);
}
