package com.spendigs.spendings;

import com.spendigs.spendings.controller.Spending;
import com.spendigs.spendings.dto.SpendingDTO;
import com.spendigs.spendings.model.User;
import com.spendigs.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.Clock;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpendingsController {

    private final UserService userService;
    Clock clock = Clock.systemDefaultZone();

    @GetMapping("/categories")
    public Set<String> getCategories(@RequestParam int userId){
        // work with HEADER or URI param?
        // userName or userId is better?
        User user = userService.findUser(userId);
        List<Spending> spendings = user.getSpendings();
        return spendings.stream().map(Spending::getCategory).collect(Collectors.toSet());
    }

    @PostMapping("/putspending")
    public void putSpending(@RequestBody SpendingDTO spendingDTO, @RequestHeader int userId){

        System.out.println("USER: " + userId + ", Spending: " + spendingDTO);

        User user = userService.findUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        Spending spending = new Spending(spendingDTO.getCategory(), spendingDTO.getAmount(), clock);
        user.addSpending(spending);
    }

    @GetMapping("/statistic")
    public Map<String, Long> getStatistic(@RequestHeader int userId,
                                          @RequestParam int year,
                                          @RequestParam String month){
        /*Looking for User*/
        User currentUser = userService.findUser(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        /*Checking URI parameters*/
        //null, negative, wrong month. Use @Valid?
        //how to check Enum fields Month using standard LocalDate class
//        Enum<Month>

        /*Running user case method*/
        if (year < 1900 || year >=3000 ) {
            return calculateSpendingsTotal(currentUser);
        } else if (month == null || "".equalsIgnoreCase(month)) {
            return calculateSpendingsYear(currentUser, year);
        } else {
            return calculateSpendingsYearMonth(currentUser, year, month);
        }

        /* Previous code to calculate total statistic
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
        return statistic;*/
    }

    private Map<String, Long> calculateSpendingsYearMonth(User currentUser, int year, String month) {
        //check Month in Enum<Month>
        // if not exist -> run
//        month = month.toUpperCase();
        List<Month> monthList = Arrays.asList(Month.values());
        Month monthToFind = monthList.stream()
                .filter(m -> m.toString().equalsIgnoreCase(month))
                .findFirst().orElse(null);
        if (monthToFind == null) {
            return calculateSpendingsYear(currentUser, year);
        }

        List<Spending> userSpendings = currentUser.getSpendings().stream()
                .filter(spending -> ((spending.getDate().getYear()) == year) 
                        && spending.getDate().getMonth().equals(monthToFind)).toList();

        Map<String, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(Spending::getCategory));
        System.out.println(collect);

        Map<String, Long> statistic = new HashMap<>();

        for (String category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToLong(Spending::getAmount).sum());
        }
        System.out.println(statistic);
        return statistic;
    }

    private Map<String, Long> calculateSpendingsYear(User currentUser, int year) {
        // okay to use whole 'database' of spendings for memory usage?
        List<Spending> userSpendings = currentUser.getSpendings().stream()
                .filter(spending -> (spending.getDate().getYear()) == year)
                .toList();

        Map<String, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(Spending::getCategory));
        System.out.println(collect);

        Map<String, Long> statistic = new HashMap<>();

        for (String category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToLong(Spending::getAmount).sum());
        }
        System.out.println(statistic);
        return statistic;
    }

    private Map<String, Long> calculateSpendingsTotal(User currentUser) {
        List<Spending> userSpendings = currentUser.getSpendings();

        Map<String, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(Spending::getCategory));
        System.out.println(collect);

        Map<String, Long> statistic = new HashMap<>();
        for (String category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToLong(Spending::getAmount)
                    .sum());
        }
        System.out.println(statistic);
        return statistic;
    }


    /*private boolean isNewUser(User user) {
        return !users.contains(user);
    }*/
}
