package bll;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
