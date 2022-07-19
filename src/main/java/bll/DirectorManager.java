package bll;

import bo.Actor;
import bo.Director;
import dal.DALException;
import dal.DAOFactory;
import dal.DirectorDAO;
/**
 * Manage queries related to the Director Class
 */
public class DirectorManager {

    private static volatile DirectorManager instance = null;

    private static DirectorDAO directorDAO = (DirectorDAO) DAOFactory.getDirectorDAO();

    public DirectorManager() {

    }

    public static final DirectorManager getInstance() {
        if(DirectorManager.instance == null) {
            synchronized (DirectorManager.class) {
                if(DirectorManager.instance == null) {
                    DirectorManager.instance = new DirectorManager();
                }
            }
        }
        return DirectorManager.instance;
    }

    /**
     *
     * @param a Director Object
     * @throws DALException
     */
    public void create(Director a) throws DALException {
        directorDAO.create(a);
    }

    /**
     *
     * @param id Director identity
     * @return
     */
    public Director getElementByIdentity(String id) {
        return directorDAO.selectByIdentity(id);
    }


}
