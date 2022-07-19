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
                    for (Actor movieActor : actorsList) {
                        System.out.println(movieActor.getIdentity());
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
