package bll;

import bo.Actor;
import bo.Genre;
import dal.*;

/**
 * Manage queries related to the Genre Class
 */
public class GenreManager {

    private static volatile GenreManager instance = null;

    private static GenreDAO genreDAO = (GenreDAO) DAOFactory.getGenreDAO();

    public GenreManager() {

    }


    public static final GenreManager getInstance() {
        if(GenreManager.instance == null) {
            synchronized (GenreManager.class) {
                if(GenreManager.instance == null) {
                    GenreManager.instance = new GenreManager();
                }
            }
        }
        return GenreManager.instance;
    }

    /**
     *
     * @param a Genre Object
     * @throws DALException
     */
    public void create(Genre a) throws DALException {
        genreDAO.create(a);
    }

    /**
     *
     * @param name Genre name
     * @return Genre object
     */
    public Genre getElementByName(String name) {
        return genreDAO.selectByName(name);
    }



}
