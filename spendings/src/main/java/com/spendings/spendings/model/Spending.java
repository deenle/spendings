package com.spendings.spendings.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "Spendings")
public class Spending {
//        private static Set<String> categories = new HashSet<>();

//        static {
//                categories.add("FOOD");
//                categories.add("ENTERTAINMENT");
//                categories.add("SPORT");
//        }

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User owner;

        @Column(name = "category")
        private String category;

        @Column(name = "amount")
        private long amount;

        @Column(name = "date")
        private LocalDate date; // to add by server

        public Spending(String category, long amount, Clock createdTime) {
//                category = category.toUpperCase();
//                categories.add(category);
                this.category = category;
                this.amount = amount;
                this.date = LocalDate.now(createdTime);
        }

        public Spending() {
        }


        public static Set<String> getSpendingCategories() {
//                return categories;
                return null;
        }
}
