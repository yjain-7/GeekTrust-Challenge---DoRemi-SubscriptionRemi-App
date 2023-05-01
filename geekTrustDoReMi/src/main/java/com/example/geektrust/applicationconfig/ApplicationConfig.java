package com.example.geektrust.applicationconfig;

import com.example.geektrust.command.*;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.service.SubscriptionService;

public class ApplicationConfig {
    static ApplicationConfig applicationSetup = new ApplicationConfig();

    private ApplicationConfig() {

    }

    public static ApplicationConfig getInstanceOfApplicationSetup() {
        return applicationSetup;
    }

    private Subscription subscription = new Subscription();
    private final SubscriptionService subscriptionService = new SubscriptionService(subscription);
    private final StartSubscription createSubscriptionCommand = new StartSubscription(subscriptionService);
    private final AddPlan addActivePlanCommand = new AddPlan(subscriptionService);
    private final AddTopUp addTopUpCommand = new AddTopUp(subscriptionService);
    private final PrintRenewalDates printRenewalDatesCommand = new PrintRenewalDates(subscriptionService);
    private final CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("START_SUBSCRIPTION", createSubscriptionCommand);
        commandInvoker.register("ADD_SUBSCRIPTION", addActivePlanCommand);
        commandInvoker.register("ADD_TOPUP", addTopUpCommand);
        commandInvoker.register("PRINT_RENEWAL_DETAILS", printRenewalDatesCommand);
        return commandInvoker;
    }
}
