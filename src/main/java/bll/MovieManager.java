package bll;

import bo.Actor;
import bo.Movie;
import dal.ActorDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.MovieDAO;

public class MovieManager {

    private static volatile MovieManager instance = null;


    private static MovieDAO movieDAO = (MovieDAO) DAOFactory.getMovieDAO();

    public MovieManager() {

    }

    public static final MovieManager getInstance() {
        if(MovieManager.instance == null) {
            synchronized (MovieManager.class) {
                if(MovieManager.instance == null) {
                    MovieManager.instance = new MovieManager();
                }
            }
        }
        return MovieManager.instance;
    }


    public void create(Movie a) throws DALException {
        movieDAO.create(a);
    }

    public Movie getElementByImdbId(String id) {
        return movieDAO.selectByImdbId(id);
    }



}
