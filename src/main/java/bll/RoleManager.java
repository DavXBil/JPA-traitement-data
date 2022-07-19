package bll;

import bo.Actor;
import bo.Role;
import dal.ActorDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.RoleDAO;

public class RoleManager {

    private static volatile RoleManager instance = null;

    private static RoleDAO roleDAO = (RoleDAO) DAOFactory.getRoleDAO();

    public RoleManager() {

    }

    public static final RoleManager getInstance() {
        if(RoleManager.instance == null) {
            synchronized (RoleManager.class) {
                if(RoleManager.instance == null) {
                    RoleManager.instance = new RoleManager();
                }
            }
        }
        return RoleManager.instance;
    }

    public void create(Role a) throws DALException {
        roleDAO.create(a);
    }

    public Role getElementByName(String name) throws DALException {
        return roleDAO.selectByName(name);
    }


}
