package com.spendigs.spendings.model;

import com.spendigs.spendings.SpendingsController;
import com.spendigs.spendings.controller.Spending;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class User {
    int id;
    String name;
    List<Spending> spendings;

    public User(String name){
        this.id = SpendingsController.USER_COUNT;
        this.name = name;
    }

    public void addSpending(Spending spending){
        this.spendings.add(spending);
    }

    public Spending getSpending(int spendingId){
        return this.spendings.get(spendingId);
    }
}
