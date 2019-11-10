package service;

import main.com.test.task.aleshin.dmitriy.countries.CountryServiceImpl;
import main.com.test.task.aleshin.dmitriy.countries.dao.CountryDAOImpl;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;
import main.com.test.task.aleshin.dmitriy.countries.model.RegionalBloc;
import main.com.test.task.aleshin.dmitriy.countries.model.Translation;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryServiceImplTest extends TestCase {

    private final static String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static boolean isInitialized = false;
    private static StandardServiceRegistry registry;
    private static Metadata metadata;
    private static SessionFactory factory;
    private static CountryDAOImpl dao;
    private static CountryServiceImpl countryService;

    @BeforeClass
    public void setUp() throws Exception {
        if (isInitialized) return;
        registry = new StandardServiceRegistryBuilder().configure(HIBERNATE_CONFIG).build();
        metadata = new MetadataSources(registry).getMetadataBuilder().build();
        factory = metadata.getSessionFactoryBuilder().build();
        dao = new CountryDAOImpl(factory);
        countryService = new CountryServiceImpl(dao);
        isInitialized = true;
    }

    @Test
    public void testCreateCountry() {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setName("AAA");
        country.setLatlng(new ArrayList<>());
        country.setCurrencies(new ArrayList<>());
        country.setLanguages(new ArrayList<>());
        country.setFlag("flag");
        country.setPopulation(123456);
        RegionalBloc regionalBloc = new RegionalBloc();
        regionalBloc.setOtherAcronyms(new ArrayList<>());
        regionalBloc.setOtherNames(new ArrayList<>());

        country.setRegionalBlocs(new ArrayList<RegionalBloc>() {
            {
                add(regionalBloc);
            }
        });
        country.setTopLevelDomain(new ArrayList<>());
        country.setTranslations(new Translation());

        countries.add(country);


        countryService.createCountries(countries);
        Optional<Country> countryOptional = countryService.getCountryByName("AAA");

        assertTrue(countryOptional.isPresent());
    }

    @Test
    public void testGetCountriesByDomain() {
        Optional<List<Country>> countries = countryService.getCountriesByDomain("a");

        assertTrue(countries.isPresent());
    }

    @Test
    public void testGetCountryByName() {
        Optional<Country> countryOptional = countryService.getCountryByName("albania");

        assertTrue(countryOptional.isPresent());
    }

    @Test
    public void testRefreshCountries() {
        countryService.refreshCountries();

        Optional<Country> countryOptional = countryService.getCountryByName("albania");

        assertTrue(countryOptional.isPresent());
    }
}
