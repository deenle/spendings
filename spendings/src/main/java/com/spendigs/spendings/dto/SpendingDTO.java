package com.spendigs.spendings.dto;

import com.spendigs.spendings.controller.Spending;
import lombok.Data;

@Data
public class SpendingDTO {

    String category;
    long amount;
}
