package bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="url")
    private String url;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country() {
    }

    public Country(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
