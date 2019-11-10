package main.com.test.task.aleshin.dmitriy.countries.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<Entity> {
    void createCountry(Entity entity);
    Optional<Entity> getCountryByName(String searchName);
    Optional<List<Entity>> getCountriesByDomain(String searchDomain);
    void deleteCountries();
}
