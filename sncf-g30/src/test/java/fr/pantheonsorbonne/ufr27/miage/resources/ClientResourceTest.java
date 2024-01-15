package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.BankGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.G30Gateway;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import fr.pantheonsorbonne.ufr27.miage.service.UpdateService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ClientResourceTest {

    @Mock
    private UpdateService updateService;

    @Mock
    private VerificationService verificationService;

    @Mock
    private CalculationService calculationService;

    @Mock
    private InsertService insertService;

    @Mock
    private BankGateway bankGateway;

    @Mock
    private G30Gateway fidelityGateway;

    @InjectMocks
    private ClientResource clientResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAskRefundEligible() throws InterruptedException {
        // Arrange
        int idTrain = 1;
        int trajetId = 2;
        int clientId = 3;
        int ticketId = 4;
        Date currentDate = new Date();

        when(verificationService.isEligibleForRefund(eq(idTrain), eq(trajetId), any(Date.class))).thenReturn(true);
        when(verificationService.isRefundExecuted(eq(ticketId))).thenReturn(Compensation.RefundStatus.ELIGIBLE);

        // Act
        Response response = clientResource.askRefund(idTrain, trajetId, clientId, ticketId);

        // Assert
        verify(fidelityGateway).startCheckFidelityEvent(eq(clientId));
        assertEquals(Response.Status.ACCEPTED.getStatusCode(), response.getStatus());
    }

    @Test
    void testAskRefundNotEligible() throws InterruptedException {
        // Arrange
        int idTrain = 1;
        int trajetId = 2;
        int clientId = 3;
        int ticketId = 4;
        Date currentDate = new Date();

        when(verificationService.isEligibleForRefund(eq(idTrain), eq(trajetId), any(Date.class))).thenReturn(false);

        // Act
        Response response = clientResource.askRefund(idTrain, trajetId, clientId, ticketId);

        // Assert
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
        verifyNoInteractions(fidelityGateway, bankGateway, updateService);
    }
}