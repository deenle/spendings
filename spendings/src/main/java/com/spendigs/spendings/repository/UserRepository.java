package com.spendigs.spendings.repository;

import com.spendigs.spendings.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Spending, Integer> {
}
