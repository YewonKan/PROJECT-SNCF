
package fr.pantheonsorbonne.ufr27.miage.dao;

        import fr.pantheonsorbonne.ufr27.miage.model.Client;
        import jakarta.enterprise.context.ApplicationScoped;
        import jakarta.inject.Inject;
        import jakarta.persistence.EntityManager;
@ApplicationScoped
public class FideliteDAOImpl implements FideliteDAO {

    @Inject
    EntityManager em;

    @Override
    public Client findFidelityById(int ticketId) {
        return em.find(Client.class, ticketId);
    }
}
