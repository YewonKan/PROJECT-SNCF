package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.TicketsGateway;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TicketDoesntExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.TicketingServices;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TicketsResourceTest {

    @Mock
    private TicketingServices ticketingServices;

    @Mock
    private TicketsGateway ticketsGateway;

    @InjectMocks
    private TicketsResource ticketsResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEmitTicket() throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        // Arrange
        int idTrip = 1;
        String fname = "Chloé";
        String lname = "SOUSSAN";
        String email = "chloe.soussan@gmail.com";
        String phone = "123456789";

        // Act
        Response response = ticketsResource.emitTicket(idTrip, fname, lname, email, phone);

        // Assert
        verify(ticketsGateway).emitTicketSendMessage(eq(idTrip), eq(fname), eq(lname), eq(email), eq(phone));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testEmitTicketWithException() throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        // Arrange
        int idTrip = 1;
        String fname = "Chloé";
        String lname = "SOUSSAN";
        String email = "chloe.soussan@gmail.com";
        String phone = "123456789";
        doThrow(new CustomersNotFoundException("Customer not found")).when(ticketsGateway).emitTicketSendMessage(anyInt(), anyString(), anyString(), anyString(), anyString());

        // Act
        Response response = ticketsResource.emitTicket(idTrip, fname, lname, email, phone);

        // Assert
        verify(ticketsGateway).emitTicketSendMessage(eq(idTrip), eq(fname), eq(lname), eq(email), eq(phone));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void testVerifyTicket() throws TicketDoesntExistException {
        // Arrange
        int idTicket = 1;

        // Act
        Response response = ticketsResource.verifyTicket(idTicket);

        // Assert
        verify(ticketingServices).verifyTicket(eq(idTicket));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testVerifyTicketNotFound() throws TicketDoesntExistException {
        // Arrange
        int idTicket = 1;
        doThrow(new TicketDoesntExistException(1)).when(ticketingServices).verifyTicket(anyInt());

        // Act
        Response response = ticketsResource.verifyTicket(idTicket);

        // Assert
        verify(ticketingServices).verifyTicket(eq(idTicket));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
}