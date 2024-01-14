package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.CompensationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.DelayInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class InsertServiceImpl implements InsertService {

    @Inject
    CompensationDAO compensationDAO;
    @Inject
    DelayInformationDAO delayInformationDAO;
    @Inject
    TicketInformationDAO ticketInformationDAO;

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public Compensation insertCompensationType(CompensationDTO compensationDTO) {
        if(compensationDAO.findByIdTicket(compensationDTO.ticketId())==null){
            Compensation newCompensation = new Compensation();
            newCompensation.setTicketId(compensationDTO.ticketId());
            newCompensation.setClient(compensationDTO.clientID());
            newCompensation.setDetail(compensationDTO.detail());
            newCompensation.setValidateDate(compensationDTO.validateDate());
            newCompensation.setType(compensationDTO.type());
            double amount = getCompensationAmount(compensationDTO.trajetId(), compensationDTO.trainId(), compensationDTO.ticketId());
            newCompensation.setAmount(amount);
            compensationDAO.insertCompensation(newCompensation);
            return newCompensation;
        }else {
            throw new RuntimeException("Compensation with ticketId " + compensationDTO.ticketId() + " already requested");
        }
    }

    @Override
    public DelayInformation insertDelayInformation(DelayInformation delayInformation) {

            DelayInformation newDelayInformation = new DelayInformation();
            newDelayInformation.setIdTrajet(delayInformation.getIdTrajet());
            newDelayInformation.setIdTrain(delayInformation.getIdTrain());
            newDelayInformation.setDelayedMinutes(delayInformation.getDelayedMinutes());
            newDelayInformation.setDelayMotivation(delayInformation.getDelayMotivation());
            newDelayInformation.setDelayedDate(delayInformation.getDelayedDate());
            delayInformationDAO.insertDelayInformation(newDelayInformation);
            return newDelayInformation;
    }

    @Override
    public TicketInformation insertTicketInformation(TicketInformation ticketInformation) {
        TicketInformation newTicketInformation = new TicketInformation();

            newTicketInformation.setClientId(ticketInformation.getClientId());
            newTicketInformation.setPrix(ticketInformation.getPrix());
            newTicketInformation.setTicketId(ticketInformation.getTicketId());
            newTicketInformation.setTrainId(ticketInformation.getTrainId());
            newTicketInformation.setTrajetId(ticketInformation.getTrajetId());

        /*TicketInformation newTicketInformation = new TicketInformation(
                ticketInformation.getTicketId(),
                ticketInformation.getTrainId(),
                ticketInformation.getTrajetId(),
                ticketInformation.getPrix(),
                ticketInformation.getClientId()
        );*/

        ticketInformationDAO.insertTicketinfo(newTicketInformation);
        return newTicketInformation;

    }



    @Override
    public double getCompensationAmount(int trajetId, int trainId, int ticketId) {
        DelayInformation delayInformation = delayInformationDAO.findById(trajetId, trainId);
        TicketInformation ticket = ticketInformationDAO.findRequestByIdTicket(ticketId);
        int delayedMinutes = delayInformation.getDelayedMinutes();
        if (delayInformation.getDelayedMinutes() >= 30 && delayedMinutes <= 59) {
            return 0.25 * ticket.getPrix();
        } else if (delayedMinutes > 60) {
            return 0.50 * ticket.getPrix();
        } else {
            return 0.0;
        }
    }
}
