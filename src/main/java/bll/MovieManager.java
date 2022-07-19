package bll;

import bo.Actor;
import bo.Movie;
import dal.ActorDAO;
import dal.DALException;
import dal.DAOFactory;
import dal.MovieDAO;

import java.util.List;


/**
 * Manage queries related to the Movie Class
 */
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

    /**
     * returns Movie object with the corresponding imdb_id
     * @param id imdb_id of Movie
     *
     */
    public Movie getElementByImdbId(String id) {
        return movieDAO.selectByImdbId(id);
    }

    /** returns Movie object having the corresponding Actor name in this actors list
     * @param name name of Actor
     *
     */
    public List<Movie> getElementByActor(String name) {
        return movieDAO.selectByActor(name);
    }

    /** returns Actor object released in an interval of two years
     * @param min first year
     * @param max second year
     *
     */
    public List<Movie> getElementByYearInterval(String min, String max) {
        return movieDAO.selectByYearInterval(min, max);
    }

    /**
     * return List of Actor having the two actors in parameters in common
     * @param actor1 first actor
     * @param actor2 second actor
     *
     */
    public List<Movie> getElementByCommonActors(String actor1, String actor2) {
        return movieDAO.selectMovieByCommonActor(actor1, actor2);
    }

    /**
     *
     * @param actor actor name
     * @param year1 first year
     * @param year2 second year
     *
     */
    public List<Movie> getElementByDateandActor(String actor, String year1, String year2) {
        return movieDAO.getFilmByActorAndDate(actor, year1, year2);
    }




}
