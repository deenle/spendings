package com.spendigs.spendings;

import com.spendigs.spendings.controller.Spending;
import com.spendigs.spendings.dto.SpendingDTO;
import com.spendigs.spendings.model.User;
import com.spendigs.spendings.service.StatisticsService;
import com.spendigs.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SpendingsController {

    private final UserService userService;
    private final StatisticsService statisticsService; // TODO: okay to do like that?
    public static final Clock clock = Clock.systemDefaultZone();
    final Logger logger = LoggerFactory.getLogger(SpendingsController.class);

    // Return All Categories where User spent money
    @GetMapping("/categories")
    public Set<String> getCategories(@RequestHeader("USER-ID") int userId) {
        // work with HEADER or URI param?
        // userName or userId is better?
        User user = userService.findUser(userId);
        List<Spending> spendings = user.getSpendings();
        return spendings.stream().map(Spending::getCategory).collect(Collectors.toSet());
    }

    // Add Spending by User
    @PostMapping("/putspending")
    public void putSpending(@RequestBody SpendingDTO spendingDTO, @RequestHeader("USER-ID") int userId) {

//        System.out.println("USER: " + userId + ", Spending: " + spendingDTO);
        logger.info("USER: " + userId + ", Spending: " + spendingDTO);

        User user = userService.findUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }

        Spending spending = new Spending(spendingDTO.getCategory(), spendingDTO.getAmount(), clock);
        user.addSpending(spending);
    }

    // Collect Spending Statistic per User
    @GetMapping("/statistic")
    public Map<String, Long> getStatistic(@RequestHeader("USER-ID") int userId,
                                          @RequestParam(required = false) Integer year,
                                          @RequestParam(required = false) String month) {
        /* Looking for User */
        User currentUser = userService.findUser(userId);
        if (currentUser == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        /* Transfer to StatisticService ==> */
        return statisticsService.calculateSpendingsByUser(currentUser, year, month);
    }


    /*private boolean isNewUser(User user) {
        return !users.contains(user);
    }*/
}
