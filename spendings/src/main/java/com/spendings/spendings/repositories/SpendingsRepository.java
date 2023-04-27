package com.spendings.spendings.repositories;

import com.spendings.spendings.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpendingsRepository extends JpaRepository<Spending, Integer> {
}
