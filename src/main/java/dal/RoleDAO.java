package dal;

import bll.ConnectionManager;
import bo.Genre;
import bo.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class RoleDAO implements DAO<Role>{

    private final EntityManagerFactory emf = ConnectionManager.getInstance().getEmf();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Role object) throws DALException {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DALException("Erreur lors de la création du film", e);
        }
    }

    @Override
    public void update(Role object) throws DALException {

    }

    @Override
    public void delete(Role object) throws DALException {

    }

    @Override
    public List<Role> selectAll() throws DALException {
        return null;
    }

    @Override
    public Role selectById(long id) throws DALException {
        return null;
    }

}
