package com.example.geektrust.entity;

import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import lombok.Getter;

import static com.example.geektrust.utils.Utils.*;

import java.util.Objects;

public class Plan {
    @Getter
    private Category planType;
    @Getter
    private SubscriptionType subscriptionType;

    public Plan(Category planType, SubscriptionType subscriptionType) {
        this.planType = planType;
        this.subscriptionType = subscriptionType;
    }

    public Integer getValueForPlan() {
        int totalAmount = 0;
        if (this.planType.equals(Category.MUSIC)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_MUSIC;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_MUSIC;
            }
        } else if (this.planType.equals(Category.VIDEO)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_VIDEO;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_VIDEO;
            }
        } else if (this.planType.equals(Category.PODCAST)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_PODCAST;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_PODCAST;
            }
        }
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan that = (Plan) o;
        return planType == that.planType && subscriptionType == that.subscriptionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planType, subscriptionType);
    }
}
