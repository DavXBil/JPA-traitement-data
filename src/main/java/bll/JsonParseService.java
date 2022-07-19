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

public class JsonParseService {

    static ActorManager actorManager = ActorManager.getInstance();
    static DirectorManager directorManager = DirectorManager.getInstance();
    static RoleManager roleManager = RoleManager.getInstance();
    static MovieManager movieManager = MovieManager.getInstance();
    static GenreManager genreManager = GenreManager.getInstance();
    static CountryManager countryManager = CountryManager.getInstance();
    static ShootingLocationManager shootingLocationManager = ShootingLocationManager.getInstance();

    public static Actor parseActorObject(JSONObject a) throws DALException {

        JSONArray actorRoles = (JSONArray) a.get("roles");

        Set<Role> roles = new HashSet<Role>();

        if(actorRoles != null) {
            for (Object role : actorRoles ) {
                roles.add(parseRolesArray((JSONObject) role));
            }
        }

        JSONObject actorBirth = (JSONObject) a.get("naissance");

        Actor actor = actorManager.getElementByIdentity((String) a.get("identite"));


        if (actor == null) {

            Actor newActor = new Actor();

            if(actorRoles != null) {
                for (Object role : actorRoles ) {
                    roles.add(parseRolesArray((JSONObject) role));
                }
                newActor.setRoles(roles);
            }

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

            newActor.setIdentity((String) a.get("identite"));


            newActor.setUrl((String) a.get("url"));
            newActor.setImdbId((String) a.get("id"));


            actorManager.create(newActor);

            actor = actorManager.getElementByIdentity((String) a.get("identite"));

        } else {

            if (actor.getBirthplace() == null || actor.getBirthdate() == null) {

                if (actorBirth != null && !actorBirth.equals("")) {


                    if (!actorBirth.get("lieuNaissance").equals("") && actorBirth.get("lieuNaissance") != null) {
                        actor.setBirthplace((String) actorBirth.get("lieuNaissance"));

                    }

                    if (!actorBirth.get("dateNaissance").equals("") && actorBirth.get("dateNaissance") != null && dateisValid((String) actorBirth.get("dateNaissance"))) {

                        String stringBirthDate = (String) actorBirth.get("dateNaissance");

                        String[] arrayBirthDate = stringBirthDate.split("-");

                        LocalDate actorBirthDate = LocalDate.of(Integer.parseInt(arrayBirthDate[0]), Integer.parseInt(arrayBirthDate[1]), Integer.parseInt(arrayBirthDate[2]));
                        actor.setBirthdate(actorBirthDate);
                    }
                }
                actorManager.update(actor);
            }
        }
        return actor;
    }

    public static Role parseRolesArray(JSONObject r) throws DALException {

        Role role =  roleManager.getElementByName((String) r.get("characterName"));

        if( role == null) {

            Role newRole = new Role();

            JSONObject roleMovie = (JSONObject) r.get("film");


            Movie movie = parseMovieObject(roleMovie);

            newRole.setCharacterName((String) r.get("characterName"));
            newRole.setMovie(movie);

            roleManager.create(newRole);

            role = roleManager.getElementByName((String) r.get("characterName"));

        }

        return role;
    }

    public static Movie parseMovieObject(JSONObject m) throws DALException {

        Movie movie = movieManager.getElementByImdbId((String) m.get("id"));

        if( movie == null) {

            Movie newMovie = new Movie();

            newMovie.setName((String) m.get("nom"));
            newMovie.setUrl((String) m.get("url"));
            newMovie.setPlot((String) m.get("plot"));
            newMovie.setImdbId((String) m.get("id"));
            newMovie.setLanguage((String) m.get("langue"));
            newMovie.setReleaseYear((String) m.get("anneeSortie"));

            if (m.get("pays") != null) {

                newMovie.setCountry(parseCountryObject((JSONObject) m.get("pays")));

            }

            if (m.get("lieuTournage") != null) {

                newMovie.setShootingLocation(parseShootingLocationObject((JSONObject) m.get("lieuTournage")));

            }

            JSONArray movieActors = (JSONArray) m.get("acteurs");

            Set<Actor> actors = new HashSet<Actor>();

            for (Object movActor : movieActors) {

                actors.add( parseActorObject((JSONObject) movActor));
            }

            JSONArray movieMainActors = (JSONArray) m.get("castingPrincipal");

            Set<Actor> mainActors = new HashSet<Actor>();

            for (Object movMainActor : mainActors) {

                actors.add( parseActorObject((JSONObject) movMainActor));
            }

            JSONArray movieDirectors = (JSONArray) m.get("realisateurs");

            Set<Director> directors = new HashSet<Director>();

            for (Object movDirector : movieDirectors) {

                directors.add( parseDirectorObject((JSONObject) movDirector));
            }

            newMovie.setActors(actors);
            newMovie.setMainActors(actors);
            newMovie.setDirectors(directors);
            newMovie.setReleaseYear((String) m.get("anneeSortie"));

            JSONArray movieGenres = (JSONArray) m.get("genres");

            Set<Genre> genres = new HashSet<Genre>();

            for (Object movGenre : movieGenres) {

                genres.add( parseGenreObject((String) movGenre));
            }

            newMovie.setGenres(genres);

            movieManager.create(newMovie);

            movie = movieManager.getElementByImdbId((String) m.get("id"));

        }

        return movie;
    }

    private static Genre parseGenreObject(String movGenre) throws DALException {
        Genre genre = genreManager.getElementByName(movGenre);

        if (genre == null) {
            Genre newGenre = new Genre();

            newGenre.setName(String.valueOf(movGenre));

            genreManager.create(newGenre);

            genre = genreManager.getElementByName(movGenre);
        }

        return genre;
    }

    public static Director parseDirectorObject(JSONObject a) throws DALException {

        Director director = directorManager.getElementByIdentity((String) a.get("identite"));


        if (director == null) {
            Director newDirector = new Director();

            newDirector.setIdentity((String) a.get("identite"));
            newDirector.setUrl((String) a.get("url"));

            directorManager.create(newDirector);

            director = directorManager.getElementByIdentity((String) a.get("identite"));
        }

        return director;
    }

    public static Country parseCountryObject(JSONObject c) throws DALException {

        Country country = countryManager.getElementByName((String) c.get("nom"));


        if (country == null) {
            Country newCountry = new Country();

            newCountry.setName((String) c.get("nom"));
            newCountry.setUrl((String) c.get("url"));

            countryManager.create(newCountry);

            country = countryManager.getElementByName((String) c.get("nom"));

        }

        return country;
    }

    public static ShootingLocation parseShootingLocationObject(JSONObject s) throws DALException {

        ShootingLocation shootLoc = shootingLocationManager.getElementByProperties((String) s.get("ville"), (String) s.get("pays"), (String) s.get("etatDept"));


        if (shootLoc == null) {
            ShootingLocation newShootLoc = new ShootingLocation();

            newShootLoc.setCity((String) s.get("ville"));
            newShootLoc.setStateDepartment((String) s.get("etatDept"));
            newShootLoc.setCountry((String) s.get("pays"));

            shootingLocationManager.create(newShootLoc);

            shootLoc = shootingLocationManager.getElementByProperties((String) s.get("ville"), (String) s.get("pays"), (String) s.get("etatDept"));

        }

        return shootLoc;
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
