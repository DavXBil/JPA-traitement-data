package dal;

import bll.ConnectionManager;
import bo.Director;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DirectorDAO implements DAO<Director>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Director object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du réalisateur", e);
        }
    }


    @Override
    public Director selectById(long id) throws DALException {
        return null;
    }
}
