package com.example.geektrust.entity;

import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SubscriptionTest {

    @ParameterizedTest
    @MethodSource("subscriptionSource")
    public void shouldGetTopUpCostTest(Subscription subscription, int expectedOutput) {
        Integer actualOutput = subscription.getTopUpCost();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldAddActivePlanWhenNonePresentTest() {
        Subscription subscription = new Subscription(LocalDate.now(), null, TopUpType.FOUR_DEVICE, 3);
        Plan activePlan = new Plan(Category.MUSIC, SubscriptionType.FREE);
        List<Plan> activePlansExpected = new ArrayList<>();
        activePlansExpected.add(activePlan);
        subscription.addPlan(activePlan);
        List<Plan> actualPlans = subscription.getActivePlans();
        Assertions.assertEquals(activePlansExpected, actualPlans);

    }

    private static Stream<Arguments> subscriptionSource() {
        return Stream.of(
                Arguments.of(new Subscription(LocalDate.now(), generateActivePlanList(), TopUpType.FOUR_DEVICE, 3), 50),
                Arguments.of(new Subscription(LocalDate.now(), generateActivePlanList(), TopUpType.TEN_DEVICE, 2), 100)
        );
    }

    private static List<Plan> generateActivePlanList() {
        List<Plan> activePlanList = new ArrayList<>();
        activePlanList.add(new Plan(Category.MUSIC, SubscriptionType.FREE));
        activePlanList.add(new Plan(Category.MUSIC, SubscriptionType.PERSONAL));
        activePlanList.add(new Plan(Category.MUSIC, SubscriptionType.PREMIUM));
        activePlanList.add(new Plan(Category.VIDEO, SubscriptionType.FREE));
        activePlanList.add(new Plan(Category.VIDEO, SubscriptionType.PERSONAL));
        activePlanList.add(new Plan(Category.VIDEO, SubscriptionType.PREMIUM));
        activePlanList.add(new Plan(Category.PODCAST, SubscriptionType.FREE));
        activePlanList.add(new Plan(Category.PODCAST, SubscriptionType.PERSONAL));
        activePlanList.add(new Plan(Category.PODCAST, SubscriptionType.PREMIUM));
        return activePlanList;
    }
}
