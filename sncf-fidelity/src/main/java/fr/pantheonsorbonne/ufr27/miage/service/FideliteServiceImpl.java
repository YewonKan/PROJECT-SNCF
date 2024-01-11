package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.ClientDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.dao.CompensationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class FideliteServiceImpl implements FideliteService {

    private final CompensationDAO compensationDAO;
    private final ClientDAO clientDAO;

    @Inject
    public FideliteServiceImpl(CompensationDAO compensationDAO, ClientDAO clientDAO) {
        this.compensationDAO = compensationDAO;
        this.clientDAO = clientDAO;
    }

    @Override
    public Compensation verifyClientStatus(Client client) throws StatutClientManquantException {
        String status = client.getStatus();

        if (status == null || status.trim().isEmpty()) {
            throw new StatutClientManquantException(client.getId());
        } else if ("grand voyageur".equalsIgnoreCase(status)) {
            return createGrandVoyageurCompensation(clientDAO.createNewClient(client.getName(), status));
        } else {
            return processNonGrandVoyageur(client);
        }
    }

    private Compensation createGrandVoyageurCompensation(Client client) {
        Compensation compensation = new Compensation(null, client, "Bon d'achat", "Présentez ce bon d'achat lors de la prochaine transaction pour bénéficier de la réduction.", "2024-12-31");
        compensationDAO.save(compensation);
        return compensation;
    }

    private Compensation processNonGrandVoyageur(Client client) {
        Compensation compensation = new Compensation(null, client, "Remboursement par chèque", "Envoyé par la poste.", null);
        compensationDAO.save(compensation);
        return compensation;
    }




}
