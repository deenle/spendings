package com.spendings.spendings.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class User {

    //    private static final AtomicInteger USER_COUNT = new AtomicInteger(0);
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

//    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
//    private List<Spending> spendings;
//    List<Spending> spendings = new CopyOnWriteArrayList<>();

    public User(String name) {
//        this.id = USER_COUNT.getAndIncrement();
        this.name = name;
    }

    public User() {
    }

    public void addSpending(Spending spending) {
//        this.spendings.add(spending);
    }

    public Spending getSpending(int spendingId) {
//        return this.spendings.get(spendingId);
        return null;
    }
}
