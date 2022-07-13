package bo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Actor extends Person{

    @Column(name="imdb_id")
    private int imdbId;

    @Column(name="birthdate")
    private LocalDate birthdate;

    @Column(name="birthplace")
    private String birthplace;

    @ManyToMany
    @JoinTable(name = "Actor_roles",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    public Actor() {
    }

    public Actor(String identity, String url, int imdbId, LocalDate birthdate, String birthplace) {
        super(identity, url);
        this.imdbId = imdbId;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getImdbId() {
        return imdbId;
    }

    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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
