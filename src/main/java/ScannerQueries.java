import bll.ActorManager;
import bll.MovieManager;
import bo.Actor;
import bo.Genre;
import bo.Movie;

import java.util.List;
import java.util.Scanner;

public class ScannerQueries {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        boolean isRunning = true;

        MovieManager movieManager = MovieManager.getInstance();
        ActorManager actorManager = ActorManager.getInstance();

        while (isRunning) {

            System.out.println("1. Affichage de la filmographie d’un acteur donné");
            System.out.println("2. Affichage du casting d’un film donné");
            System.out.println("3. Affichage des films sortis entre 2 années données");
            System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés.");
            System.out.println("5. Affichage des acteurs communs à 2 films donnés");
            System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
            System.out.println("7. Fin de l’application");

            int menuInput = scan.nextInt();
            scan.nextLine();

            switch (menuInput) {
                case 1 -> {
                    System.out.println("Tapez le nom d'un(e) acteur/actrice");
                    String input = scan.nextLine();
                    List<Movie> movieList = movieManager.getElementByActor(input);
                    System.out.println("Voici la filmographie de " + input + ":");
                    for (Movie actorMovie : movieList) {
                        System.out.println(actorMovie.getName());
                    }
                }

                case 2 -> {
                    System.out.println("Entrez le titre d'un film");
                    String input = scan.nextLine();
                    List<Actor> actorsList = actorManager.getCastByFilm(input);
                    System.out.println("Voici le casting de " + input + ":");
                    for (Actor movieActor : actorsList) {
                        System.out.println(movieActor.getIdentity());
                    }

                }

                case 3 -> {
                    System.out.println("Entrez une première année");
                    String input = scan.nextLine();
                    System.out.println("Entrez une seconde année");
                    String input2 = scan.nextLine();
                    List<Movie> movieList = movieManager.getElementByYearInterval(input, input2);
                    System.out.println("Voici les films sortis entre" + input + " et " + input2);
                    for (Movie actorMovie : movieList) {
                        System.out.println(actorMovie.getName());
                    }
                }

                case (4) -> {
                    System.out.println("* - Veuillez indiquer le 1er acteur/actrice");
                    String input = scan.nextLine();
                    System.out.println("* - Veuillez indiquer le 2ème acteur/actrice");
                    String input2 = scan.nextLine();
                    List<Movie> commonActorsMovies = movieManager.getElementByCommonActors(input, input2);

                    System.out.println("Voici les films commun entre " + input + " et " + input2 + " :");
                    for (Movie movie : commonActorsMovies) {
                        System.out.println(movie.getName());
                    }
                }

                case (5) -> {
                    System.out.println("* - Veuillez indiquer le 1er film");
                    String input = scan.nextLine();
                    System.out.println("* - Veuillez indiquer le 2ème film");
                    String input2 = scan.nextLine();
                    List<Actor> acteurCommunEntre1Film = actorManager.geElementByCommonMovie(input, input2);

                    System.out.println("Voici les acteurs commun entre " + input + " et " + input2 + " :");
                    for (Actor acteur : acteurCommunEntre1Film) {
                        System.out.println(acteur.getIdentity());
                    }
                }

                case (6) -> {
                    System.out.println("* - Veuillez indiquer la 1ère année");
                    String input = scan.nextLine();
                    System.out.println("* - Veuillez indiquer la 2ème année");
                    String input2 = scan.nextLine();
                    System.out.println("* - Veuillez indiquer un acteur/actrice");
                    String input3 = scan.nextLine();
                    List<Movie> movies = movieManager.getElementByDateandActor(input3, input, input2);

                    System.out.println("Voici les films qui ont " + input3 + " et qui ont été sortie entre " + input + " et " + input2 + " :");
                    for (Movie movie : movies) {
                        System.out.println(movie.getName());
                    }
                }

                case 7 -> {
                    isRunning = false;
                    System.out.println("Au revoir");
                    scan.close();
                }
            }
        }
    }
}
