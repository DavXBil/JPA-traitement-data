package bll;

import bo.*;
import dal.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class JsonParseManager {

    static ActorDAO actorDAO = (ActorDAO) DAOFactory.getActorDAO();
    static RoleDAO roleDAO = (RoleDAO) DAOFactory.getRoleDAO();
    static MovieDAO movieDAO = (MovieDAO) DAOFactory.getMovieDAO();

    public static Actor parseActorObject(JSONObject a) throws DALException {

        Actor actor = actorDAO.selectByIdentity((String) a.get("identite"));


        if (actor == null) {
            Actor newActor = new Actor();

            JSONObject actorBirth = (JSONObject) a.get("naissance");

            if (actorBirth != null) {
                if(!actorBirth.toString().equals("")) {
                    newActor.setBirthplace((String) actorBirth.get("lieuNaissance"));
                }

                String stringBirthDate = (String) actorBirth.get("dateNaissance");


                if(dateisValid(stringBirthDate)) {
                    String[] arrayBirthDate = stringBirthDate.split("-");

                    LocalDate actorBirthDate = LocalDate.of(Integer.parseInt(arrayBirthDate[0]), Integer.parseInt(arrayBirthDate[1]), Integer.parseInt(arrayBirthDate[2]));
                    newActor.setBirthdate(actorBirthDate);
                }

            }

            JSONArray actorRoles = (JSONArray) a.get("roles");

            Set<Role> roles = new HashSet<Role>();

            if(actorRoles != null) {
                for (Object role : actorRoles ) {
                    roles.add(parseRolesArray((JSONObject) role));
                }
                newActor.setRoles(roles);
            }

            newActor.setIdentity((String) a.get("identite"));


            newActor.setUrl((String) a.get("url"));
            newActor.setImdbId((String) a.get("id"));


            actorDAO.create(newActor);

            actor = actorDAO.selectByIdentity((String) a.get("identite"));
        }

        return actor;
    }

    public static Role parseRolesArray(JSONObject r) throws DALException {

        Role role =  roleDAO.selectById((String) r.get("characterName"));

        if( role == null) {

            Role newRole = new Role();

            JSONObject roleMovie = (JSONObject) r.get("film");


            Movie movie = parseMovieObject(roleMovie);

            newRole.setCharacterName((String) r.get("characterName"));
            newRole.setMovie(movie);

            roleDAO.create(newRole);

            role = roleDAO.selectById((String) r.get("characterName"));

        }

        return role;
    }

    public static Movie parseMovieObject(JSONObject m) throws DALException {

        Movie movie = movieDAO.selectByImdbId((String) m.get("id"));

        if( movie == null) {

            Movie newMovie = new Movie();

            newMovie.setName((String) m.get("nom"));
            newMovie.setUrl((String) m.get("url"));
            newMovie.setPlot((String) m.get("plot"));
            newMovie.setImdbId((String) m.get("id"));
            newMovie.setLanguage((String) m.get("langue"));
            newMovie.setReleaseYear((String) m.get("anneeSortie"));

            JSONArray movieActors = (JSONArray) m.get("acteurs");

            Set<Actor> actors = new HashSet<Actor>();

            for (Object movActor : movieActors) {
                System.out.println("There => " + movActor);
                actors.add( parseActorObject((JSONObject) movActor));
            }

            newMovie.setActors(actors);

            movieDAO.create(newMovie);

            movie = movieDAO.selectByImdbId((String) m.get("id"));

        }

        return movie;
    }

    public static boolean dateisValid(String dateStr) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format1.setLenient(false);
            format1.parse(dateStr);
        } catch (DateTimeParseException | ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
