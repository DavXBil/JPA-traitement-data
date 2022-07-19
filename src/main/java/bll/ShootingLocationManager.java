package bll;

import bo.Actor;
import bo.ShootingLocation;
import dal.DALException;
import dal.DAOFactory;
import dal.ShootingLocationDAO;

public class ShootingLocationManager {

    private static volatile ShootingLocationManager instance = null;

    private static ShootingLocationDAO shootingLocationDAO = (ShootingLocationDAO) DAOFactory.getShootingLocationDAO();

    public ShootingLocationManager() {

    }

    public static final ShootingLocationManager getInstance() {
        if(ShootingLocationManager.instance == null) {
            synchronized (ShootingLocationManager.class) {
                if(ShootingLocationManager.instance == null) {
                    ShootingLocationManager.instance = new ShootingLocationManager();
                }
            }
        }
        return ShootingLocationManager.instance;
    }


    public void create(ShootingLocation a) throws DALException {
        shootingLocationDAO.create(a);
    }

    public ShootingLocation getElementByProperties(String city, String country, String department) {
        return shootingLocationDAO.selectByProperties(city, country, department);
    }



}
