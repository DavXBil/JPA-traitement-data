import bll.ConnectionManager;
import bll.FileManager;
import bll.JsonParseManager;
import bo.Actor;
import dal.ActorDAO;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {


    public static void main(String[] args) {

        JsonParseManager jsonParser = new JsonParseManager();

        DAO<Actor> actorDAO = DAOFactory.getActorDAO();

        JSONArray jsonList = FileManager.readCsv("src/main/resources/films.json");

        if (jsonList != null) {
            jsonList.forEach( emp -> {
                try {
                    jsonParser.parseActorObject( (JSONObject) emp );
                } catch (DALException e) {
                    throw new RuntimeException(e);
                }
            });
        }


    }


}
