package com.spendigs.spendings;

import com.spendigs.spendings.controller.Spending;
import com.spendigs.spendings.dto.SpendingDTO;
import com.spendigs.spendings.model.User;
import com.spendigs.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpendingsController {

    private final UserService userService;

    @GetMapping("/categories")
    public Set<String> getCategories(){
        return Spending.getSpendingCategories();
    }

    @PostMapping("/putspending")
//    public void putSpending(@RequestBody String name, Spending spending){
    public void putSpending(@RequestBody SpendingDTO spendingDTO, @RequestHeader int userId){

        System.out.println("USER: " + userId + ", Spending: " + spendingDTO);

        User user = userService.findUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        Spending spending = new Spending(spendingDTO.getCategory(), spendingDTO.getAmount());
        user.addSpending(spending);
    }

    @GetMapping("/statistic")
    public Map<String, Long> getStatistic(@RequestHeader int userId){
        /*looking for User*/
        User currentUser = userService.findUser(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        List<Spending> userSpendings = currentUser.getSpendings();

        Map<String, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(spending -> spending.getCategory()));
        System.out.println(collect);

        Map<String, Long> statistic = new HashMap<>();
        for (String category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToLong(spending -> spending.getAmount()).sum());
        }
        System.out.println(statistic);
        return statistic;
    }


    /*private boolean isNewUser(User user) {
        return !users.contains(user);
    }*/
}
