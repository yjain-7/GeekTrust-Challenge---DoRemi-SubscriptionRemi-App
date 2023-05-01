
package com.example.geektrust.service;

import com.example.geektrust.entity.Plan;
import com.example.geektrust.entity.RenewalDetails;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import com.example.geektrust.exception.DoRemiException;

import static com.example.geektrust.utils.Utils.generateRenewalReminderDate;

import java.time.LocalDate;
import java.util.List;

public class SubscriptionService {
    private Subscription subscription;

    public SubscriptionService(Subscription subscription) {
        this.subscription = subscription;
    }

    public void createSubscription(LocalDate date) {
        this.subscription.setDateOfSubscription(date);
    }

    public void addPlan(String planType, String subscriptionType) throws DoRemiException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new DoRemiException("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
        }
        Category planTypeEnum = Category.valueOf(planType);
        SubscriptionType subscriptionTypeEnum = SubscriptionType.valueOf(subscriptionType);
        Plan activePlan = new Plan(planTypeEnum, subscriptionTypeEnum);
        boolean isDuplicatePlan = checkDuplicatePlan(this.subscription.getActivePlans(), planTypeEnum);
        if (isDuplicatePlan) {
            throw new DoRemiException("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        subscription.addPlan(activePlan);
    }

    private boolean checkDuplicatePlan(List<Plan> activePlans, Category planTypeEnum) {
        if (activePlans == null) return false;
        return activePlans.stream().allMatch(obj -> obj.getPlanType().equals(planTypeEnum));
    }

    public void addTopUpPlan(String topUpType, int noOfMonths) throws DoRemiException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new DoRemiException("ADD_TOPUP_FAILED INVALID_DATE");
        }
        TopUpType topUpTypeEnum = TopUpType.valueOf(topUpType);
        if (this.subscription.getTopUpType() != null && this.subscription.getTopUpType().equals(topUpTypeEnum)) {
            throw new DoRemiException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        this.subscription.setTopUpType(topUpTypeEnum);
        this.subscription.setTopUpValidity(noOfMonths);
    }

    public List<RenewalDetails> printRenewalDetails() throws DoRemiException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new DoRemiException("SUBSCRIPTIONS_NOT_FOUND");
        }
        List<Plan> activePlanList = subscription.getActivePlans();
        return generateRenewalReminderDate(this.subscription.getDateOfSubscription(), activePlanList);
    }

    public Integer getPlanValue() {
        List<Plan> activePlanList = subscription.getActivePlans();
        Integer totalCost = 0;
        for (Plan activePlan : activePlanList) {
            totalCost += activePlan.getValueForPlan();
        }
        totalCost += subscription.getTopUpType() != null ? subscription.getTopUpCost() * subscription.getTopUpValidity() : 0;
        return totalCost;
    }
}
