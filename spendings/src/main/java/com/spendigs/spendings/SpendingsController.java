package com.spendigs.spendings;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/v1")
public class SpendingsController {

    private final Set<String> categories = new HashSet<>();

    private final Map<String, Spending> users = new HashMap<>();

    {

        users.put("Jack", null);
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        return new ArrayList<>(categories);
    }

    @PostMapping("/putspending")
    public void putSpenging(@RequestBody Spending spending){
        spending.date = LocalDate.now();
        categories.add(spending.category);
        users.put("Jack", spending);
        System.out.println(spending);
    }

    @GetMapping("/statistic")
    public void getStatistic(@RequestParam("user") String user){
        Spending spending = users.get(user);
        StringBuilder sb = new StringBuilder();
        sb.append("For user ")
                .append(user).append(" ")
                .append(spending.category).append(" ")
                .append(spending.amount).append(" ")
                .append(spending.date).append(" ");
        System.out.println(sb);
    }

    @Data
    private static class Spending {
        String category;
        long amount;
        LocalDate date; // to add by server
    }
}
