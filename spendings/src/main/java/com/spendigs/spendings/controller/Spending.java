package com.spendigs.spendings.controller;

import lombok.Data;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Spending {
        private static Set<String> categories = new HashSet<>();

        static {
                categories.add("FOOD");
                categories.add("ENTERTAINMENT");
                categories.add("SPORT");
        }

        String category;
        long amount;
        LocalDate date; // to add by server

        public Spending(String category, long amount, Clock createdTime){
                this.category = checkCategory(category);
                this.amount = amount;
                this.date = LocalDate.now(createdTime);
        }

        private String checkCategory(String category) {
                if (!categories.contains(category)) {
                        categories.add(category.toUpperCase());
                }
                return category.toUpperCase();
        }

        public static Set<String> getSpendingCategories(){
                return categories;
        }
}
