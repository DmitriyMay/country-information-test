package main.com.test.task.aleshin.dmitriy.countries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "level_domain")
public class Domain {

    private int id;
    private String definition;
    private Country country;
    private int couId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "def")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cou_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "cou_id", insertable = false, updatable = false)
    public int getCou_id() {
        return couId;
    }

    public void setCou_id(int cou_id) {
        this.couId = cou_id;
    }
}
