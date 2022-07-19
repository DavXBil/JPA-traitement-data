package bll;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * reads a Json file and initiate the parsing of the classes in it
 */
public class FileManager {


    public static JSONArray readCsv(String filePath) {
        JSONParser jsonParser = new JSONParser();

        JSONArray jsonList = null;
        try (FileReader reader = new FileReader(filePath)) {

            Object obj = jsonParser.parse(reader);

            jsonList = (JSONArray) obj;


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonList;

    }

}
