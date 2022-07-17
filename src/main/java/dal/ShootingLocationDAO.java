package dal;

import bll.ConnectionManager;
import bo.ShootingLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    @Override
    public ShootingLocation selectById(long id) throws DALException {
        return null;
    }
}
