package bll;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager {

    private static ConnectionManager instance;

    public ConnectionManager() {
        this.emf = Persistence.createEntityManagerFactory("movie-jpa");
    }

    public static ConnectionManager getInstance() {
        if(instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    private final EntityManagerFactory emf;



}
