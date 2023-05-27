package com.spendings.spendings.dto;

import com.spendings.spendings.model.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
//TODO refactor to record?
public class SpendingDTO {

    @NotEmpty
    Category category;

    @Min(value = 0)
    Double amount;

    LocalDate date;

    //TODO to add static conversion <-> methods?
    // public static SpendingDTO fromEntity(Spending spending)
}
