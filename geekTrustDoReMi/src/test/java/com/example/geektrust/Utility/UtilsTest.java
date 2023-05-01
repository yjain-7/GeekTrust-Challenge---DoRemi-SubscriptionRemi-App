package com.example.geektrust.Utility;

import com.example.geektrust.entity.Plan;
import com.example.geektrust.entity.RenewalDetails;
import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.utils.Utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UtilsTest {
    @Test
    public void shouldGenerateRenewalReminderDateForActivePlansTest() {
        List<RenewalDetails> renewalDetailsDTOSExpected = generateRenewalDetailsDTOList();
        LocalDate date = LocalDate.of(2022, 01, 20);
        List<RenewalDetails> renewalDetailsDTOSActual = Utils.generateRenewalReminderDate(date, generateActivePlanList());
        Assertions.assertEquals(renewalDetailsDTOSExpected, renewalDetailsDTOSActual);
    }

    @ParameterizedTest
    @MethodSource("activePlansSource")
    public void ShouldGetActivePlanValidityInMonthsTest(Plan activePlan, int validityExpected) {
        int actualValidity = Utils.getActivePlanValidityInMonths(activePlan);
        Assertions.assertEquals(validityExpected, actualValidity);
    }

    private static Stream<Arguments> activePlansSource() {
        return Stream.of(
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.FREE), 1),
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.PERSONAL), 1),
                Arguments.of(new Plan(Category.MUSIC, SubscriptionType.PREMIUM), 3),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.FREE), 1),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.PERSONAL), 1),
                Arguments.of(new Plan(Category.VIDEO, SubscriptionType.PREMIUM), 3),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.FREE), 1),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.PERSONAL), 1),
                Arguments.of(new Plan(Category.PODCAST, SubscriptionType.PREMIUM), 3)
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

    private static List<RenewalDetails> generateRenewalDetailsDTOList() {
        List<RenewalDetails> renewalDetailsDTOS = new ArrayList<>();
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 04, 10), Category.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 04, 10), Category.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.PODCAST));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 02, 10), Category.PODCAST));
        renewalDetailsDTOS.add(new RenewalDetails(LocalDate.of(2022, 04, 10), Category.PODCAST));

        return renewalDetailsDTOS;
    }
}
