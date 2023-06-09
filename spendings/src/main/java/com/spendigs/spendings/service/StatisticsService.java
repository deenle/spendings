package com.spendigs.spendings.service;

import com.spendigs.spendings.SpendingsController;
import com.spendigs.spendings.controller.Spending;
import com.spendigs.spendings.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {


    public Map<String, Long> calculateSpendingsByUser(User currentUser, Integer year, String month) {
        /*Choosing user case method*/ //TODO need to correct conditions?
        if ((year == null && month == null) || (year != null && (year < 1900 || year >= LocalDate.MAX.getYear()))) {
            return calculateSpendingsTotal(currentUser);
        } else if (year != null && (month == null || "".equalsIgnoreCase(month))) {
            return calculateSpendingsYear(currentUser, year);
        } else {
            return calculateSpendingsYearMonth(currentUser, year, month);
        }
    }

    private Map<String, Long> calculateSpendingsYearMonth(User currentUser, Integer year, String month) {

        if (year == null) {
            year = LocalDate.now(SpendingsController.clock).getYear();
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

    private Map<String, Long> calculateSpendingsYear(User currentUser, int year) {
        List<Spending> userSpendings = currentUser.getSpendings().stream()
                .filter(spending -> (spending.getDate().getYear() == year))
                .toList();

        return convertSpendingsToMap(userSpendings);
    }

    private Map<String, Long> calculateSpendingsTotal(User currentUser) {
        List<Spending> userSpendings = currentUser.getSpendings();

        return convertSpendingsToMap(userSpendings);
    }

    private Map<String, Long> convertSpendingsToMap(List<Spending> userSpendings) {
        Map<String, List<Spending>> collect = userSpendings.stream()
                .collect(Collectors.groupingBy(Spending::getCategory));
//        System.out.println(collect);

        Map<String, Long> statistic = new HashMap<>();
        for (String category : collect.keySet()) {
            statistic.put(category, collect.get(category).stream()
                    .mapToLong(Spending::getAmount)
                    .sum());
        }
        System.out.println(statistic);
        return statistic;
    }
}
