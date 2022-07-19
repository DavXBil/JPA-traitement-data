import bll.FileManager;
import bll.JsonParseService;
import bo.Actor;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Parsing {


    public static void main(String[] args) {

        JsonParseService jsonParser = new JsonParseService();

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
