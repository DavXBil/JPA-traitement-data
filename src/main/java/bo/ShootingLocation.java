package bo;

import javax.persistence.*;

@Entity
public class ShootingLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "state_department")
    private Long stateDepartment;

    @Column(name = "country")
    private Long country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShootingLocation() {
    }

    public ShootingLocation(String city, Long stateDepartment, Long country) {
        this.city = city;
        this.stateDepartment = stateDepartment;
        this.country = country;
    }
}
