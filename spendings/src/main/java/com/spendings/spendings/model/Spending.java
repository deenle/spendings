package com.spendings.spendings.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Spendings")
public class Spending {

        static final Logger logger = LoggerFactory.getLogger(Spending.class);

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
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private Date date; // to add by server

        public Spending(Category category, double amount, Clock createdTime) {
//                category = category.toUpperCase();
//                categories.add(category);
                logger.debug("Spending constructor working with category {}, amount  {}, createdTime {}",
                        category, amount, createdTime);

                this.category = category;
                this.amount = amount;
                this.date = Date.from(Instant.now(createdTime));

        }

        public Spending() {
                logger.debug("Empty Spending constructor working");
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
