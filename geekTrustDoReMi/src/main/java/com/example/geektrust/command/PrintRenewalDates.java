package com.example.geektrust.command;

import com.example.geektrust.entity.RenewalDetails;
import com.example.geektrust.service.SubscriptionService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrintRenewalDates implements CommandInterface {
    private final SubscriptionService subscriptionService;

    public PrintRenewalDates(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            List<RenewalDetails> renewalDateStrings = subscriptionService.printRenewalDetails();
            Integer renewalAmount = subscriptionService.getPlanValue();
            for (RenewalDetails renewalDetailsDTO : renewalDateStrings) {
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String dateString = renewalDetailsDTO.getDate().format(formatters);
                System.out.println("RENEWAL_REMINDER " + renewalDetailsDTO.getPlanType() + " " + dateString);
            }
            System.out.println("RENEWAL_AMOUNT " + renewalAmount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
