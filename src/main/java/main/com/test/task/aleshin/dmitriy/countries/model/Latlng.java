package main.com.test.task.aleshin.dmitriy.countries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "latlng")
public class Latlng {

    private int id;
    private double latlng;
    private Country country;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "latlng_v")
    public double getLatlng() {
        return latlng;
    }

    public void setLatlng(double latlng) {
        this.latlng = latlng;
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
}
