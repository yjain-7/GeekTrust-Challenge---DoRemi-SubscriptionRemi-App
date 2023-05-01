package com.example.geektrust.command;

import com.example.geektrust.service.SubscriptionService;

import java.util.List;

public class AddTopUp implements CommandInterface {
    private final SubscriptionService subscriptionService;
    private static final int topUpPlan_index = 1;
    private static final int noOfMonths_index = 2;

    public AddTopUp(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            subscriptionService.addTopUpPlan(commandParts.get(topUpPlan_index), Integer.parseInt(commandParts.get(noOfMonths_index)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
