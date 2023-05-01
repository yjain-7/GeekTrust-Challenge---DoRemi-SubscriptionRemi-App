package com.example.geektrust.service;


import com.example.geektrust.entity.Plan;
import com.example.geektrust.entity.RenewalDetails;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.enums.Category;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import com.example.geektrust.exception.DoRemiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("SubscriptionServiceTest")
@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @Mock
    private Subscription subscriptionMock;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    public void shouldThrowErrorWhenSubscriptionNotCreatedOnAddingNewActivePlanTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(DoRemiException.class, () -> subscriptionService.addPlan(Category.PODCAST.toString(), SubscriptionType.PREMIUM.toString()));
    }

    @Test
    public void shouldThrowErrorOnAddingDuplicatePlan() throws DoRemiException {
        List<Plan> activePlans = new ArrayList<>();
        activePlans.add(new Plan(Category.MUSIC, SubscriptionType.FREE));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getActivePlans()).thenReturn(activePlans);
        Assertions.assertThrows(DoRemiException.class, () -> subscriptionService.addPlan(Category.MUSIC.toString(), SubscriptionType.FREE.toString()));
    }

    @Test
    public void shouldThrowErrorOnAddingTopUpPlanWhenNoSubscriptionFoundTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(DoRemiException.class, () -> subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1));
    }

    @Test
    public void shouldThrowErrorOnAddingDuplicateTopUpPlanTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getTopUpType()).thenReturn(TopUpType.TEN_DEVICE);
        Assertions.assertThrows(DoRemiException.class, () -> subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1));
    }

    @Test
    public void shouldAddTopUpPlan() throws DoRemiException {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getTopUpType()).thenReturn(null);
        subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1);
        verify(subscriptionMock, times(1)).setTopUpType(TopUpType.TEN_DEVICE);
        verify(subscriptionMock, times(1)).setTopUpValidity(1);

    }

    @Test
    public void shouldThrowErrorOnPrintRenewalDetailsTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(DoRemiException.class, () -> subscriptionService.printRenewalDetails());

    }

    @Test
    public void printRenewalDetailsTest() throws DoRemiException {
        List<RenewalDetails> expectedRenewalDetailsDTO = new ArrayList<>();
        expectedRenewalDetailsDTO.add(new RenewalDetails(LocalDate.of(2022, 02, 05), Category.MUSIC));
        expectedRenewalDetailsDTO.add(new RenewalDetails(LocalDate.of(2022, 02, 05), Category.VIDEO));
        expectedRenewalDetailsDTO.add(new RenewalDetails(LocalDate.of(2022, 04, 05), Category.PODCAST));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.of(2022, 01, 15));
        when(subscriptionMock.getActivePlans()).thenReturn(generateActivePlanList());
        List<RenewalDetails> renewalDetailsDTOSActual = subscriptionService.printRenewalDetails();
        Assertions.assertEquals(expectedRenewalDetailsDTO, renewalDetailsDTOSActual);

    }

    @Test
    public void createSubscriptionTest() {
        LocalDate date = LocalDate.now();
        subscriptionService.createSubscription(date);
        verify(subscriptionMock, times(1)).setDateOfSubscription(date);
    }

    @Test
    public void getPlanValueTest() {
        Integer expected = 500;
        when(subscriptionMock.getActivePlans()).thenReturn(generateActivePlanList());
        when(subscriptionMock.getTopUpType()).thenReturn(TopUpType.TEN_DEVICE);
        when(subscriptionMock.getTopUpCost()).thenReturn(100);
        Integer actual = subscriptionService.getPlanValue();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddActivePlanTest() throws DoRemiException {
        List<Plan> activePlans = new ArrayList<>();
        activePlans.add(new Plan(Category.VIDEO, SubscriptionType.FREE));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getActivePlans()).thenReturn(activePlans);
        Plan activePlan = new Plan(Category.MUSIC, SubscriptionType.FREE);
        subscriptionService.addPlan(Category.MUSIC.toString(), SubscriptionType.FREE.toString());
        verify(subscriptionMock, times(1)).addPlan(activePlan);
    }

    private static List<Plan> generateActivePlanList() {
        List<Plan> activePlanList = new ArrayList<>();
        activePlanList.add(new Plan(Category.MUSIC, SubscriptionType.FREE));
        activePlanList.add(new Plan(Category.VIDEO, SubscriptionType.PERSONAL));
        activePlanList.add(new Plan(Category.PODCAST, SubscriptionType.PREMIUM));
        return activePlanList;
    }


}
