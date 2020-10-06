package com.bilyoner.assignment.balanceapi.repository;

import com.bilyoner.assignment.balanceapi.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
