package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dao.TicketsDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/emitTickets")
    public Response emitTicket(@QueryParam("idTrip") int idTrip, @QueryParam("fname") String fname, @QueryParam("lname") String lname, @QueryParam("email") String email, @QueryParam("phone") String phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try {
            ticketingServices.emitTicket(idTrip, fname, lname, email, phone);
            return Response.ok("Tickets created successfully").build();

        } catch (CustomersNotFoundException | TripNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }




    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, World!";
    }

}

