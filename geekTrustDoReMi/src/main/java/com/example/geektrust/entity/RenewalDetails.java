package com.example.geektrust.entity;

import com.example.geektrust.enums.Category;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

public class RenewalDetails {
    @Getter
    private LocalDate date;
    @Getter
    private Category planType;

    public RenewalDetails(LocalDate date, Category planType) {
        this.date = date;
        this.planType = planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenewalDetails that = (RenewalDetails) o;
        return Objects.equals(date, that.date) && planType == that.planType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, planType);
    }

    @Override
    public String toString() {
        return "RenewalDetailsDTO{" +
                "date=" + date +
                ", planType=" + planType +
                '}';
    }
}
