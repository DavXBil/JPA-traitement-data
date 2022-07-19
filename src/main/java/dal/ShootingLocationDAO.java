package dal;

import bll.ConnectionManager;
import bo.Country;
import bo.ShootingLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class ShootingLocationDAO implements DAO<ShootingLocation>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(ShootingLocation object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du film", e);
        }
    }

    public ShootingLocation selectByProperties(String city, String country, String department) {
        TypedQuery<ShootingLocation> query = em.createQuery("select r from ShootingLocation r where r.city = :city and r.country = :country and stateDepartment = :department ", ShootingLocation.class);
        query.setParameter("city", city);
        query.setParameter("country", country);
        query.setParameter("department", department);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }
}
