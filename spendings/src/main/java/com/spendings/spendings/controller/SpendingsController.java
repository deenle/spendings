package com.spendings.spendings.controller;

import com.spendings.spendings.dto.SpendingDTO;
import com.spendings.spendings.model.Spending;
import com.spendings.spendings.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpendingsController {

    // TODO Return ResponseEntity<>?
//    private final UserService userService;
    private final StatisticsService statisticsService;

    // TODO Foresee isLoggable() level!
    // TODO static or not?
    final Logger logger = LoggerFactory.getLogger(SpendingsController.class);

    // TODO where is better to use Clock?
    public static final Clock clock = Clock.systemDefaultZone();

    // Return All Categories where User spent money
    /*@GetMapping("/categories")
    public Set<String> getCategories(@RequestHeader("USER-ID") int userId) {
        logger.info("getCategories method working");
        User user = userService.findOne(userId);
        List<Spending> spendings = user.getSpendings();
        return spendings.stream().map(Spending::getCategory).collect(Collectors.toSet());
    }*/

    // Add Spending by User
    @PostMapping("/putspending")
    public void putSpending(@RequestHeader("USER-ID") int userId, @RequestBody SpendingDTO spendingDTO) {
        logger.debug("USER: {}, Spending: {}", userId, spendingDTO);

//        User user = userService.findOne(userId);
//        if (user == null) {
//            throw new IllegalArgumentException("User with ID " + userId + " not found");
//        }

//        Spending spending = new Spending(spendingDTO.getCategory(), spendingDTO.getAmount(), clock);
//        user.addSpending(spending);
    }

    // Collect Spending Statistic per User
    @GetMapping("/statistic")
    public Map<String, Long> getStatistic(@RequestHeader("USER-ID") int userId,
                                          @RequestParam(required = false) Integer year,
                                          @RequestParam(required = false) String month) {
        logger.debug("statistic method working");
        /* Looking for User */
//        User currentUser = userService.findOne(userId);
//        if (currentUser == null) {
//            throw new IllegalArgumentException("User with ID " + userId + " not found");
//        }
        /* Transfer to StatisticService ==> */
//        return statisticsService.calculateSpendingsByUser(currentUser, year, month);
        return null;
    }

    @GetMapping("/{id}")
    public Spending getSpendingById(@PathVariable(name = "id") int id) {
        logger.debug("getSpendingById working for id: {} working from {}", id, SpendingsController.class.getSimpleName());
        return statisticsService.findOne(id);
    }

    /*private boolean isNewUser(User user) {
        return !users.contains(user);
    }*/
}
