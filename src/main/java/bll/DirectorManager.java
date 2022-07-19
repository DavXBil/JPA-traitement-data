package bll;

import bo.Actor;
import bo.Director;
import dal.DALException;
import dal.DAOFactory;
import dal.DirectorDAO;

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

    public void create(Director a) throws DALException {
        directorDAO.create(a);
    }

    public Director getElementByIdentity(String id) {
        return directorDAO.selectByIdentity(id);
    }


}
