import bll.ConnectionManager;
import bll.FileManager;
import bll.JsonParseManager;
import bo.Actor;
import dal.ActorDAO;
import dal.DAO;
import dal.DAOFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        JsonParseManager jsonParser = new JsonParseManager();

        DAO<Actor> actorDAO = DAOFactory.getActorDAO();

        JSONArray jsonList = FileManager.readCsv("src/main/resources/films.json");

        if (jsonList != null) {
            jsonList.forEach( emp -> jsonParser.parseActorObject( (JSONObject) emp ) );
        }


    }


}
