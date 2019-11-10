package main.com.test.task.aleshin.dmitriy.countries.dao.mock;

import main.com.test.task.aleshin.dmitriy.countries.dao.DAO;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSource implements DAO<Country> {
    private List<Country> countries = new ArrayList<>();

    @Override
    public void createCountry(Country country) {
        countries.add(country);
    }

    @Override
    public Optional<Country> getCountryByName(String searchName) {
        for (Country country : countries) {
            if (isNameExists(country, searchName)) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }

    private boolean isNameExists(Country country, String searchName) {
        return country.getName().equalsIgnoreCase(searchName);
    }

    @Override
    public Optional<List<Country>> getCountriesByDomain(String searchDomain) {
        List<Country> searchedCountries = new ArrayList<>();

        for (Country country : countries) {
            if (isDomainContains(country, searchDomain)) {
                searchedCountries.add(country);
            }
        }

        return Optional.of(searchedCountries);
    }

    private boolean isDomainContains(Country country, String searchDomain) {
        return country.getTopLevelDomain().stream().anyMatch(d -> d.getDefinition().contains(searchDomain));
    }

    @Override
    public void deleteCountries() {
        countries.clear();
    }

    public int getSize() {
        return countries.size();
    }
}
