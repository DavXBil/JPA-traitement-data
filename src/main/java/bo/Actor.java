package bo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity

public class Actor extends Person{

    @Column(name="imdb_id")
    private String imdbId;

    /*Birthdate of actor*/
    @Column(name="birthdate")
    private LocalDate birthdate;

    /*Birthplace of actor*/
    @Column(name="birthplace")
    private String birthplace;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.MERGE)
    private Set<Movie> movies = new LinkedHashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Actor_roles",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Actor() {
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        System.out.println(birthdate);
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Actor{");
        sb.append(super.toString());
        sb.append("imdbId=").append(imdbId);
        sb.append(", birthdate=").append(birthdate);
        sb.append(", birthplace='").append(birthplace).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
