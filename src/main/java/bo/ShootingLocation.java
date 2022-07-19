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
    private String stateDepartment;

    @Column(name = "country")
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateDepartment() {
        return stateDepartment;
    }

    public void setStateDepartment(String stateDepartment) {
        this.stateDepartment = stateDepartment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ShootingLocation() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShootingLocation{");
        sb.append("city='").append(city).append('\'');
        sb.append(", stateDepartment='").append(stateDepartment).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
