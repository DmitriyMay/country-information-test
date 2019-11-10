package main.com.test.task.aleshin.dmitriy.countries.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    private int id;
    private String name;
    private List<Domain> topLevelDomain;
    private long population;
    private List<Latlng> latlng;
    private List<Currency> currencies;
    private List<Language> languages;
    private Translation translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cou_id")
    public List<Domain> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<Domain> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    @Column(name = "population")
    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cou_id")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Latlng> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Latlng> latlng) {
        this.latlng = latlng;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cou_id")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Currency> getCurrencies() {
        return this.currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cou_id")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "translation_id")
    public Translation getTranslations() {
        return translations;
    }

    public void setTranslations(Translation translations) {
        this.translations = translations;
    }

    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cou_id")
    @Fetch(value = FetchMode.SUBSELECT)
    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }
}
