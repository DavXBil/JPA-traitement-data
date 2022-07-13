package bll;

import bo.Actor;
import org.json.simple.JSONObject;

import java.time.LocalDate;

public class JsonParseManager {

    public static void parseActorObject(JSONObject a)  {
        Actor actor = new Actor();
        JSONObject actorBirth = (JSONObject) a.get("naissance");
        System.out.println(actorBirth);
        String actorBirthDate = (String) actorBirth.get("dateNaissance");
        String[] arrayBirthDate = actorBirthDate.split("-");
        actor.setIdentity((String) a.get("identite"));
        actor.setBirthdate((LocalDate) actorBirth.get(LocalDate.of(Integer.parseInt(arrayBirthDate[0]), Integer.parseInt(arrayBirthDate[1]), Integer.parseInt(arrayBirthDate[2]))));
        System.out.println(actor);

    }
}
