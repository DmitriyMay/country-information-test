package main.com.test.task.aleshin.dmitriy.countries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "regional_bloc")
public class RegionalBloc {

    private int id;
    private String acronym;
    private String name;
    private List<OtherAcronym> otherAcronyms;
    private List<OtherName>  otherNames;
    private Country country;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "regional_bloc_id")
    public List<OtherAcronym>  getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<OtherAcronym>  otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "regional_bloc_id")
    public List<OtherName>  getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<OtherName>  otherNames) {
        this.otherNames = otherNames;
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
