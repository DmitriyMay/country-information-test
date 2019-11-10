package main.com.test.task.aleshin.dmitriy.countries;

import java.util.List;
import java.util.Optional;

public interface MyService<Entity> {

    void createCountries(List<Entity> countries);
    Optional<Entity> getCountryByName(String searchName);
    Optional<List<Entity>> getCountriesByDomain(String searchDomain);
    void refreshCountries();
}
