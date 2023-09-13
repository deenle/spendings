package com.spendings.spendings.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Slf4j
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Spendings")
public class Spending {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        //        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "user_id")
//        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User owner;

        @Enumerated(EnumType.STRING)
        @Column(name = "category")
        @NotEmpty
        private Category category;

        @Column(name = "amount")
        @Min(value = 0)
        private Double amount;

        @Column(name = "date")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        private LocalDate date;

        public Spending(Category category, Double amount, LocalDate createdTime) {
//                category = category.toUpperCase();
//                categories.add(category);
                log.debug("Spending constructor working with category {}, amount  {}, createdTime {}",
                        category, amount, createdTime);

                this.category = category;
                this.amount = amount;
                //TODO Howto use Clock here?
                this.date = createdTime;
        }

//        public Spending() {
//                log.debug("Empty Spending constructor working");
//        }

        @Override
        public String toString() {
                return "Spending{" +
                        "id=" + id +
                        ", category='" + category + '\'' +
                        ", amount=" + amount +
                        ", date=" + date +
                        '}';
        }
}
