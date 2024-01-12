package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.TicketsGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketsDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TicketDoesntExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customers;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;
import fr.pantheonsorbonne.ufr27.miage.model.Trip;
import fr.pantheonsorbonne.ufr27.miage.service.TicketingServices;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tickets")
public class TicketsResource {
    @Inject
    TicketingServices ticketingServices;


    @Inject
    TicketsGateway ticketsGateway;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/emitTickets")
    public Response emitTicket(@QueryParam("idTrip") int idTrip, @QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("email") String email, @QueryParam("phone") String phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try {
            //ticketingServices.emitTicket(idTrip, fname, lname, email, phone);
            ticketsGateway.emitTicketSendMessage(idTrip, fname, lname, email, phone);
            return Response.ok("Tickets created successfully").build();

        } catch (CustomersNotFoundException | TripNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verifyTicket")
    public Response verifyTicket(@QueryParam("idTicket") int idTicket) {
        try {
            ticketingServices.verifyTicket(idTicket);
            String jsonResponse = "{\"status\": \"success\", \"message\": \"Ticket verified successfully\"}";
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (TicketDoesntExistException ex) {
            String errorResponse = "{\"status\": \"error\", \"message\": \"Ticket not found\"}";
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    private String ticketToJson(Tickets ticket) {

        return "";
    }

}

