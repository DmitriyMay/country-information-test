package main.com.test.task.aleshin.dmitriy.countries.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "other_name")
public class OtherName {

    private int id;
    private String otherName;
    private RegionalBloc regionalBlocs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "other_name")
    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "regional_bloc_id", referencedColumnName = "id")
    public RegionalBloc getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(RegionalBloc regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }
}
