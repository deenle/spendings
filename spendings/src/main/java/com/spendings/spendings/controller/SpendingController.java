package com.spendings.spendings.controller;

import com.spendings.spendings.dto.SpendingDTO;
import com.spendings.spendings.model.Category;
import com.spendings.spendings.model.Spending;
import com.spendings.spendings.model.User;
import com.spendings.spendings.service.StatisticsService;
import com.spendings.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/spendings")
@RequiredArgsConstructor
public class SpendingController {

    // TODO Need to return ResponseEntity<?> ?
//    private final UserService userService;
    private final StatisticsService statisticsService;
    private final UserService userService;

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
        log.debug("USER: {}, Spending: {}", userId, spendingDTO);

//        User user = userService.findOne(userId);
//        if (user == null) {
//            throw new IllegalArgumentException("User with ID " + userId + " not found");
//        }

//        Spending spending = new Spending(spendingDTO.getCategory(), spendingDTO.getAmount(), clock);
//        user.addSpending(spending);
    }

    // Collect Spending Statistic per User
    @GetMapping("/statistic")
    public Map<Category, Double> getStatistic(@RequestHeader("USER-ID") int userId,
                                              @RequestParam(required = false) Integer year,
                                              @RequestParam(required = false) String month) {
        log.debug("statistic method working");

        /* Looking for User */
        User currentUser = userService.findOne(userId); //TODO Okay to use User model here? no need DTO or smth?
        if (currentUser == null) {
            //TODO return ResponseEntity<>(HttpStatus.NOT_FOUND) => need to write @ExceptionHandler
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        /* Transfer to StatisticService ==> */
        return statisticsService.calculateSpendingsByUser(currentUser, year, month);
    }

    @GetMapping("/{id}")
    public SpendingDTO getSpendingById(@PathVariable(name = "id") int id) {
        log.debug("getSpendingById working for id: {} working from {}", id, SpendingController.class.getSimpleName());
        Spending spending = statisticsService.findOne(id);
        return SpendingDTO.builder()
                .amount(spending.getAmount())
                .category(spending.getCategory())
                .date(spending.getDate())
                .build();
    }
}
