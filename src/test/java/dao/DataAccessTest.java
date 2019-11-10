package dao;

import main.com.test.task.aleshin.dmitriy.countries.CountryServiceImpl;
import main.com.test.task.aleshin.dmitriy.countries.MyService;
import main.com.test.task.aleshin.dmitriy.countries.dao.mock.DataSource;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DataAccessTest extends TestCase {


    MyService service;

    @Before
    public void setUp() throws Exception {
        service = new CountryServiceImpl(new DataSource());
        service.refreshCountries();
    }

    @Test
    public void testCreateCountries() {
        Country country = createCountry();
        List<Country> countries = new ArrayList<>();
        countries.add(country);

        service.createCountries(countries);
        Optional<Country> c = service.getCountryByName("aaa");

        assertTrue(c.isPresent());
    }

    private Country createCountry() {
        Country country = new Country();
        country.setName("aaa");

        return country;
    }

    @Test
    public void testGetCountryByName() {
        Optional<Country> country = service.getCountryByName("Afghanistan");

        assertTrue(country.isPresent());
    }

    @Test
    public void testGetCountriesByDomain() {
        Optional<List<Country>> countries = service.getCountriesByDomain("x");

        assertTrue(countries.isPresent());
    }

    @Test
    public void testRefreshCountries() {
        service.refreshCountries();
        Optional<Country> country = service.getCountryByName("Afghanistan");

        assertTrue(country.isPresent());
    }
}
