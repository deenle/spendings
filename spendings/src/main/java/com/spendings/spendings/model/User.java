package com.spendings.spendings.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {

    // TODO why non-static not working?
    static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    //    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Spending> spendings;

    public User(String name) {
        logger.debug("User constructor with name: {} working", name);
        this.name = name;
    }

    public User() {
        logger.debug("Empty User constructor working");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /*public void addSpending(Spending spending) {
//        this.spendings.add(spending);
    }

    public Spending getSpending(int spendingId) {
//        return this.spendings.get(spendingId);
        return null;
    }*/
}
