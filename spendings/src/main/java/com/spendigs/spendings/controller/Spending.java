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
                category = category.toUpperCase();
                categories.add(category);
                this.category = category;
                this.amount = amount;
                this.date = LocalDate.now(createdTime);
        }


        public static Set<String> getSpendingCategories(){
                return categories;
        }
}
