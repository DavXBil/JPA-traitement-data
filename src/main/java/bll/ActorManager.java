package bll;

import bo.Actor;
import dal.ActorDAO;
import dal.DALException;
import dal.DAOFactory;

import java.util.List;

/**
 * Manage queries related to the Actor Class
 */
public class ActorManager {

    private static volatile ActorManager instance = null;

    private static ActorDAO actorDAO = (ActorDAO) DAOFactory.getActorDAO();

    public ActorManager() {

    }

    public static final ActorManager getInstance() {
        if(ActorManager.instance == null) {
            synchronized (ActorManager.class) {
                if(ActorManager.instance == null) {
                    ActorManager.instance = new ActorManager();
                }
            }
        }
        return ActorManager.instance;
    }


    public void update(Actor a) throws DALException {
        actorDAO.update(a);

    }

    public void create(Actor a) throws DALException {
        actorDAO.create(a);
    }

    /**
     * returns Actor object with the corresponding imdb_id
     * @param id imdb_id of Actor
     *
     */
    public Actor getElementByImdbId(String id) {
        return actorDAO.selectByImdbId(id);
    }

    /** returns Actor object with the corresponding name
     * @param name name of Actor
     *
     */
    public Actor getElementByIdentity(String name) {
        return actorDAO.selectByIdentity(name);
    }

    /**
    * return a List of Actor of a Movie
     * @param movie movie name
     *
     */
    public List<Actor> getCastByFilm(String movie) {
        return actorDAO.selectCastByMovie(movie);
    }

    /**
     * return a List of Actor who have been in the same movie
     * @param movie1 first movie name
     * @param movie2 second movie name
     *
     *
     */
    public List<Actor> geElementByCommonMovie(String movie1, String movie2) {
        return actorDAO.selectByCommonMovie(movie1, movie2);
    }



}
