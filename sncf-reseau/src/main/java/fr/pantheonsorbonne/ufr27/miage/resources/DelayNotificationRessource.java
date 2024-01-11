package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.DelayNotificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("delay-notification")
public class DelayNotificationRessource {
    @Inject
    DelayNotificationService delayNotificationService;
    @Path("/sendDelayNotification")
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    public Response sendDelayNotification(String idTrain, int delayDuration, String reason, LocalDateTime creationTime) {
        DelayNotification notification = delayNotificationService.sendDelayNotification(idTrain, delayDuration, reason, creationTime);

        return Response.ok(notification).build();
    }


}
