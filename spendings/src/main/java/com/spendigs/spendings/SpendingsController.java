package com.spendigs.spendings;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SpendingsController {

    private final Set<String> categories = new HashSet<>();

    @GetMapping("/v1/categories")
    public List<String> getCategories(){
        return new ArrayList<>(categories);
    }

    @PostMapping("/v1/putspendings")
    public void putSpengings(@RequestBody Spending spending){
        categories.add(spending.category);
        System.out.println(spending);
    }

    @Data
    private static class Spending {
        String category;
        long amount;
    }
}
