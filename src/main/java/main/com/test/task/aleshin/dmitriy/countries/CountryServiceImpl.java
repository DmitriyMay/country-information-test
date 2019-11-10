package main.com.test.task.aleshin.dmitriy.countries;

import main.com.test.task.aleshin.dmitriy.countries.dao.DAO;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("myService")
public class CountryServiceImpl implements MyService<Country> {

    @Autowired
    @Qualifier("implCountryDAO")
    private DAO dao;

    public CountryServiceImpl(DAO dao) {
        this.dao = dao;
        cacheLoad();
    }

    private void cacheLoad() {
        List<JSONObject> jsonObjects = getJsonObjects();
        List<Country> countries = getCountries(jsonObjects);
        createCountries(countries);
    }

    private List<JSONObject> getJsonObjects() {
        return new JsonLoader().getJsonObjects();
    }

    private List<Country> getCountries(List<JSONObject> jsonObjects) {
        return new JsonParser().getCountriesFromJson(jsonObjects);
    }

    @Override
    public void createCountries(List<Country> countries) {
        for (Country country : countries) {
            dao.createCountry(country);
        }
    }

    @Override
    public synchronized Optional<Country> getCountryByName(String search) {
        return dao.getCountryByName(search);
    }

    @Override
    public synchronized Optional<List<Country>> getCountriesByDomain(String searchDomain) {
        return dao.getCountriesByDomain(searchDomain);
    }

    private void deleteCountries() {
         dao.deleteCountries();
    }

    @Override
    public synchronized void refreshCountries() {
        deleteCountries();
        cacheLoad();
    }
}
