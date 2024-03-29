package com.spendings.spendings.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private List<Spending> spendings;

    public User(String name) {
        log.debug("User constructor with name: {} working", name);
        this.name = name;
    }

//    public User() {
//        log.debug("Empty User constructor working");
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
