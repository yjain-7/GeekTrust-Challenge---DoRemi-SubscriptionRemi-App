package com.example.geektrust.command;

import com.example.geektrust.exception.DoRemiException;
import com.example.geektrust.service.SubscriptionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("CreateSubscriptionCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddPlanTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Mock
    private SubscriptionService subscriptionServiceMock;

    @InjectMocks
    private AddPlan addPlan;

    @Test
    public void shouldAddToActivePlansTest() throws DoRemiException {
        List<String> list = new ArrayList<>();
        list.add("ADD_SUBSCRIPTION");
        list.add("MUSIC");
        list.add("PERSONAL");
        addPlan.execute(list);
        verify(subscriptionServiceMock, times(1)).addPlan("MUSIC", "PERSONAL");
    }

    @Test
    public void shouldFailToAddToActivePlansWhenDuplicateTest() throws DoRemiException {
        List<String> list = new ArrayList<>();
        String expected = "ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY";
        doThrow(new DoRemiException(expected)).when(subscriptionServiceMock).addPlan("MUSIC", "PERSONAL");
        list.add("ADD_SUBSCRIPTION");
        list.add("MUSIC");
        list.add("PERSONAL");
        addPlan.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
