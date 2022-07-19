package bll;

import bo.Actor;
import bo.Country;
import dal.CountryDAO;
import dal.DALException;
import dal.DAOFactory;

public class CountryManager {

    private static volatile CountryManager instance = null;

    static CountryDAO countryDAO = (CountryDAO) DAOFactory.getCountryDAO();

    public CountryManager() {

    }

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


    public void create(Country a) throws DALException {
        countryDAO.create(a);
    }

    public Country getElementByName(String name) {
        return countryDAO.selectByName(name);
    }


}
