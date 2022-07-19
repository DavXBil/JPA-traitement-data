package bo;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "movie", orphanRemoval = true, cascade = CascadeType.MERGE)
    private Set<Role> roles = new LinkedHashSet<>();

    public Movie() {
    }

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


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shooting_location_id")
    private ShootingLocation shootingLocation;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pays_id")
    private Country country;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id"))
    private Set<Actor> actors = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Movie_main_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_id"))
    private Set<Actor> mainActors = new LinkedHashSet<>();

    public Set<Actor> getMainActors() {
        return mainActors;
    }

    public void setMainActors(Set<Actor> mainActors) {
        this.mainActors = mainActors;
    }

    public Set<Actor> getActors() {
        return actors;
    }



    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
