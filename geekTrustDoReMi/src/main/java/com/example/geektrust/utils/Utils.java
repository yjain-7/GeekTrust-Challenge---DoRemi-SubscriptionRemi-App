
package com.example.geektrust.utils;

import com.example.geektrust.entity.Plan;
import com.example.geektrust.entity.RenewalDetails;
import com.example.geektrust.enums.SubscriptionType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static final int FREE_PLAN = 0;
    public static final int PERSONAL_MUSIC = 100;
    public static final int PREMIUM_MUSIC = 250;

    public static final int PERSONAL_VIDEO = 200;
    public static final int PREMIUM_VIDEO = 500;

    public static final int PERSONAL_PODCAST = 100;
    public static final int PREMIUM_PODCAST = 300;

    public static final int FREE_AND_PERSONAL = 1;
    public static final int PREMIUM_PLAN = 3;

    public static final int NO_DAYS_BEFORE_RENEWAL = 10;

    public static List<RenewalDetails> generateRenewalReminderDate(LocalDate subscriptionDate, List<Plan> activePlans) {
        return activePlans.stream().map(obj -> getRenewalDetails(subscriptionDate, obj)).collect(Collectors.toList());
    }

    public static RenewalDetails getRenewalDetails(LocalDate subscriptionDate, Plan obj) {
        return new RenewalDetails(generateRenewalReminderDate(subscriptionDate, obj), obj.getPlanType());
    }

    public static LocalDate generateRenewalReminderDate(LocalDate subscriptionDate, Plan obj) {
        return subscriptionDate.plusMonths(getActivePlanValidityInMonths(obj)).minusDays(NO_DAYS_BEFORE_RENEWAL);
    }

    public static int getActivePlanValidityInMonths(Plan activePlan) {
        SubscriptionType subscriptionType = activePlan.getSubscriptionType();
        if (subscriptionType.equals(SubscriptionType.FREE) || subscriptionType.equals(SubscriptionType.PERSONAL)) {
            return FREE_AND_PERSONAL;
        }
        return PREMIUM_PLAN;
    }
}
