package bll;

import bo.Actor;
import dal.ActorDAO;
import dal.DALException;
import dal.DAOFactory;

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

    public Actor getElementByImdbId(String id) {
        return actorDAO.selectByImdbId(id);
    }
    public Actor getElementByIdentity(String id) {
        return actorDAO.selectByIdentity(id);
    }


}
