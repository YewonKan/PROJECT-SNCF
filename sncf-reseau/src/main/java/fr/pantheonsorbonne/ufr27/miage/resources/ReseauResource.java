package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.NotificationDelayGateway;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reseau")
public class ReseauResource {
    @Inject
    ReseauService reseauService;
    @Inject
    NotificationDelayGateway notificationDelayGateway;

    @POST
    @Path("/delay")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendDelayNotification(DelayNotification d) {
        DelayNotification notification = reseauService.sendDelayNotification(d.getIdTrain(),d.getIdTrajet(), d.getDelayDuration(), d.getReason(), d.getCreationTime());
        //Injection de la route qui envoie
        notificationDelayGateway.sendDelayNotification(d.getIdTrain(),d.getIdTrajet(),d.getDelayDuration(),d.getReason(),d.getCreationTime());
        return Response.ok(notification).build();
    }


}
