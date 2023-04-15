package com.spendigs.spendings.model;

import com.spendigs.spendings.controller.Spending;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class User {

    private static final AtomicInteger USER_COUNT = new AtomicInteger(0);

    int id;
    String name;
    List<Spending> spendings = new ArrayList<>();

    public User(String name){
        this.id = USER_COUNT.getAndIncrement();
        this.name = name;
    }

    public void addSpending(Spending spending){
        this.spendings.add(spending);
    }

    public Spending getSpending(int spendingId){
        return this.spendings.get(spendingId);
    }
}
