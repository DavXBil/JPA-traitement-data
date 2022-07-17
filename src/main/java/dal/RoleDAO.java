package dal;

import bll.ConnectionManager;
import bo.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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
    public Role selectById(long id) throws DALException {
        return null;
    }

    public Role selectById(String characterName) throws DALException {

        TypedQuery<Role> query = em.createQuery("select r from Role r where r.characterName = :name", Role.class);
        query.setParameter("name", characterName);
        return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
    }

}
