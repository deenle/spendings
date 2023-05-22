package com.spendings.spendings.service;

import com.spendings.spendings.model.Category;
import com.spendings.spendings.model.Spending;
import com.spendings.spendings.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsService {

//    private final SpendingRepository spendingRepository;


    public Map<Category, Double> calculateSpendingsByUser(User currentUser, Integer year, String month) {
        //*Choosing user case method*//* //TODO need to correct conditions?
        if ((year == null && month == null) || (year != null && (year < 1900 || year >= LocalDate.MAX.getYear()))) {
            return calculateSpendingsTotal(currentUser);
        } else if (year != null && (month == null || "".equalsIgnoreCase(month))) {
            return calculateSpendingsYear(currentUser, year);
        } else {
            return calculateSpendingsYearMonth(currentUser, year, month);
        }
    }

    private Map<Category, Double> calculateSpendingsYearMonth(User currentUser, Integer year, String month) {

        if (year == null) {
            // TODO howto use Clock from Controller for testing?
//            year = LocalDate.now(SpendingsController.clock).getYear();
            year = LocalDate.now().getYear();
        }

        List<Month> monthList = Arrays.asList(Month.values());
        Month monthToFind = monthList.stream()
                .filter(m -> m.toString().equalsIgnoreCase(month))
                .findFirst().orElse(null);
        if (monthToFind == null) {
            return calculateSpendingsYear(currentUser, year);
        }

        Integer finalYear = year;
        List<Spending> userSpendings = currentUser.getSpendings().stream()
                .filter(spending -> ((spending.getDate().getYear()) == finalYear)
                        && spending.getDate().getMonth().equals(monthToFind))
                .toList();

        return convertSpendingsToMap(userSpendings);
    }

    private Map<Category, Double> calculateSpendingsYear(User currentUser, int year) {
        List<Spending> userSpendings = currentUser.getSpendings().stream()
                .filter(spending -> (spending.getDate().getYear() == year))
                .toList();

        return convertSpendingsToMap(userSpendings);
    }

    private Map<Category, Double> calculateSpendingsTotal(User currentUser) {
        List<Spending> userSpendings = currentUser.getSpendings();

        return convertSpendingsToMap(userSpendings);
    }

    private Map<Category, Double> convertSpendingsToMap(List<Spending> userSpendings) {
        Map<Category, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(Spending::getCategory));
//        System.out.println(collect);

        Map<Category, Double> statistic = new HashMap<>();
        for (Category category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToDouble(Spending::getAmount)
                    .sum());
        }
        System.out.println(statistic);
        return statistic;
    }
}
