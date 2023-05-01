package com.example.geektrust.command;
import com.example.geektrust.service.SubscriptionService;
import java.util.List;

public class AddPlan implements CommandInterface {

    private final SubscriptionService subscriptionService;
    private static final int planType_index = 1;
    private static final int categoryType_index = 2;

    public AddPlan(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            subscriptionService.addPlan(commandParts.get(planType_index), commandParts.get(categoryType_index));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
