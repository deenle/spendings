package com.spendings.spendings.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Set;

@Data
@Slf4j
@Entity
@Table(name = "Spendings")
public class Spending {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        // TODO Why Bi-direct not working?
//        @ManyToOne
//        @JoinColumn(name = "user_id", referencedColumnName = "id")
//        private User owner;

        @Enumerated(EnumType.STRING)
        @Column(name = "category")
        private Category category;

        @Column(name = "amount")
        private double amount;

        @Column(name = "date")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private LocalDate date; // to add by server

        public Spending(Category category, double amount, Clock createdTime) {
//                category = category.toUpperCase();
//                categories.add(category);
                log.debug("Spending constructor working with category {}, amount  {}, createdTime {}",
                        category, amount, createdTime);

                this.category = category;
                this.amount = amount;
                this.date = LocalDate.now(createdTime);
        }

        public Spending() {
                log.debug("Empty Spending constructor working");
        }

        @Override
        public String toString() {
                return "Spending{" +
                        "id=" + id +
                        ", category='" + category + '\'' +
                        ", amount=" + amount +
                        ", date=" + date +
                        '}';
        }

        public static Set<String> getSpendingCategories() {
//                return categories;
                return null;
        }
}
