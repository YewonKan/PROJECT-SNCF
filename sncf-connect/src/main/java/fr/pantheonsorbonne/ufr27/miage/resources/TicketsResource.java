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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TicketsResource {
    @Inject
    TicketingServices ticketingServices;


    @POST
    @Path(("/emitTickets"))
    public Response emitTicket(@QueryParam("idTrip") int idTrip,@QueryParam("fname")  String fname,@QueryParam("lname")  String lname,@QueryParam("email")  String email, @QueryParam("phone") int phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try {
            ticketingServices.emitTicket(idTrip, fname, lname, email, phone);
            return Response.ok("Tickets created successfully").build();

        } catch (CustomersNotFoundException | TripNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

  /*  @POST
    @Path(("/emitTickets"))
    public Response emitTicket(Trip t, Customers c) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try{
            if (t.getIdTrip()==null || t.getQuota()==null){
                return Response.status(422).build();
            }
            Tickets result = ticketingServices.emitTicket(t.getIdTrip(), c.getIdCostumer(), c.getFname(), c.getLname(), c.getEmail(), c.getPhone());
            return Response.ok(result).build();
        } catch (CustomersNotFoundException | TripNotFoundException | NoAvailablePlaces e) {
            return Response.status(422).build();
        }catch (Exception e) {
            return Response.status(500).build();
        }*/



}

