package bo;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "plot")
    private String plot;
    @Column(name = "imdb_id")
    private String imdbId;
    @Column(name = "language")
    private String language;
    @Column(name = "release_year")
    private String releaseYear;

    public Movie() {
    }

    public Movie(String name, String url, String plot, String imdbId, String language, String releaseYear) {
        this.name = name;
        this.url = url;
        this.plot = plot;
        this.imdbId = imdbId;
        this.language = language;
        this.releaseYear = releaseYear;
    }

    @ManyToMany
    @JoinTable(name = "Movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id"))
    private Set<Actor> actors = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "Movie_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "directors_id"))
    private Set<Director> directors = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "Movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private Set<Genre> genres = new LinkedHashSet<>();



    @ManyToOne
    @JoinColumn(name = "shooting_location_id")
    private ShootingLocation shootingLocation;

    @ManyToOne
    @JoinColumn(name = "pays_id")
    private Country country;

    public Country getPays() {
        return country;
    }

    public void setPays(Country country) {
        this.country = country;
    }

    public ShootingLocation getShootingLocation() {
        return shootingLocation;
    }

    public void setShootingLocation(ShootingLocation shootingLocation) {
        this.shootingLocation = shootingLocation;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }


    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
