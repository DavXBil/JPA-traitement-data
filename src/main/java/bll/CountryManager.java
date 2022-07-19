package bll;

import bo.Actor;
import bo.Country;
import dal.CountryDAO;
import dal.DALException;
import dal.DAOFactory;
/**
 * Manage queries related to the Country Class
 */
public class CountryManager {

    private static volatile CountryManager instance = null;

    static CountryDAO countryDAO = (CountryDAO) DAOFactory.getCountryDAO();

    public CountryManager() {

    }

    /**
     *
     * @return instance of CountryManager
     */
    public static final CountryManager getInstance() {
        if(CountryManager.instance == null) {
            synchronized (CountryManager.class) {
                if(CountryManager.instance == null) {
                    CountryManager.instance = new CountryManager();
                }
            }
        }
        return CountryManager.instance;
    }


    /**
     *
     * @param a Country object
     * @throws DALException
     */
    public void create(Country a) throws DALException {
        countryDAO.create(a);
    }

    /**
     *
     * @param name Country name
     */
    public Country getElementByName(String name) {
        return countryDAO.selectByName(name);
    }


}
