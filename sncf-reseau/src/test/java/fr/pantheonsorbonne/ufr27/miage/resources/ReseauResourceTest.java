package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.NotificationDelayGateway;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReseauResourceTest {

    @Mock
    private ReseauService reseauService;

    @Mock
    private NotificationDelayGateway notificationDelayGateway;

    @InjectMocks
    private ReseauResource reseauResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendDelayNotification() {

        DelayNotification inputDelayNotification = new DelayNotification();
        DelayNotification outputDelayNotification = new DelayNotification();


        when(reseauService.sendDelayNotification(
                inputDelayNotification.getIdTrain(),
                inputDelayNotification.getIdTrajet(),
                inputDelayNotification.getDelayDuration(),
                inputDelayNotification.getReason(),
                inputDelayNotification.getCreationTime()))
                .thenReturn(outputDelayNotification);

        // Act
        Response response = reseauResource.sendDelayNotification(inputDelayNotification);

        // Assert

        verify(reseauService).sendDelayNotification(
                inputDelayNotification.getIdTrain(),
                inputDelayNotification.getIdTrajet(),
                inputDelayNotification.getDelayDuration(),
                inputDelayNotification.getReason(),
                inputDelayNotification.getCreationTime());


        verify(notificationDelayGateway).sendDelayNotification(
                inputDelayNotification.getIdTrain(),
                inputDelayNotification.getIdTrajet(),
                inputDelayNotification.getDelayDuration(),
                inputDelayNotification.getReason(),
                inputDelayNotification.getCreationTime());


        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(outputDelayNotification, response.getEntity());
    }

}
