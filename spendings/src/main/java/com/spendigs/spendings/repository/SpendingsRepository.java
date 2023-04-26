package com.spendigs.spendings.repository;

import com.spendigs.spendings.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingsRepository extends JpaRepository<User, Integer> {
}
