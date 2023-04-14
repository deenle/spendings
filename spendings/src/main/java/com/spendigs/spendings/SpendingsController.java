package com.spendigs.spendings;

import com.spendigs.spendings.controller.Spending;
import com.spendigs.spendings.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1")
public class SpendingsController {

//    private final Set<String> categories = new HashSet<>();
    public static int USER_COUNT; // For User id++
    private final List<User> users = new ArrayList<>();

    @GetMapping("/categories")
    public Set<String> getCategories(){
        return Spending.getSpendingCategories();
    }

    @PostMapping("/putspending")
//    public void putSpending(@RequestBody String name, Spending spending){
    public void putSpending(@RequestBody User user){
        /**
         How to read new Spending from POST request and add that spending (Jackson?)???
         */
        Spending spending = user.getSpending(0); // parsing spending at zero position?
        if (isNewUser(user)){
            users.add(user); // no need to add new spending ?
            USER_COUNT++;
        } else {
            int id = user.getId();
            User existedUser = users.get(id);
            existedUser.addSpending(spending);
            users.set(id, existedUser);
        }
        System.out.println(user.getName() + " lost money on: " + spending);
    }

    @GetMapping("/statistic")
    public void getStatistic(@RequestParam("user") String user){
        List<Spending> userSpendings = users.get(users.indexOf(user)).getSpendings();

        System.out.println("Spendings for user " + user + ":\n");

        for (Spending spending : userSpendings) {
            StringBuilder sb = new StringBuilder();
            sb.append(spending.getCategory()).append(" : ")
                    .append(spending.getAmount()).append(" : ")
                    .append(spending.getDate()).append("\n");
            System.out.println(sb);
        }
    }

    private boolean isNewUser(User user) {
        return !users.contains(user);
    }
}
