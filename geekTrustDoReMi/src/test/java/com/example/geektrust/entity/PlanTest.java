package com.example.geektrust.entity;

import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PlanTest {

    @ParameterizedTest
    @MethodSource("activePlanInputSource")
    public void shouldGetValueForPlanTest(Plan activePlan, Integer expectedOutput) {
        Integer actualOutput = activePlan.getValueForPlan();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    private static Stream<Arguments> activePlanInputSource() {
        return Stream.of(
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.FREE), 0),
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.PERSONAL), 100),
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.PREMIUM), 250),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.FREE), 0),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.PERSONAL), 200),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.PREMIUM), 500),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.FREE), 0),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.PERSONAL), 100),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.PREMIUM), 300)
        );
    }

}
