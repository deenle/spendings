package com.spendings.spendings.dto;

import com.spendings.spendings.model.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SpendingDTO {

    Category category;
    double amount;
    LocalDate date;
}
