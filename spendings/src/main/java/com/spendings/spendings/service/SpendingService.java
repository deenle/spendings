package com.spendings.spendings.service;

import com.spendings.spendings.model.Spending;
import com.spendings.spendings.repositories.SpendingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpendingService {

    private final SpendingRepository spendingRepository;

    public Spending findOne(int id) {
        log.debug("findOne working for id: {} from {} class working", id, StatisticsService.class.getSimpleName());
        return spendingRepository.getReferenceById(id);
    }

    public List<Spending> findAll() {
        log.debug("findAll from {} class working", StatisticsService.class.getSimpleName());
        return spendingRepository.findAll();
    }

    @Transactional
    public void save(Spending spending) {
        spendingRepository.save(spending);
    }
/*    public void addSpendingByUser(User user, Spending spending){
        //TODO Add data to DB
        spendingRepository.save(spending);
    }*/
}
