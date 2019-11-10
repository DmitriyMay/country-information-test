package controller;

import main.com.test.task.aleshin.dmitriy.controller.CountryController;
import main.com.test.task.aleshin.dmitriy.countries.CountryServiceImpl;
import main.com.test.task.aleshin.dmitriy.countries.dao.CountryDAOImpl;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.http.ResponseEntity;

public class CountriesControllerTest extends TestCase {

    private final static String HIBERNATE_CONFIG = "hibernate.cfg.xml";
    private static boolean isInitialized = false;
    private static StandardServiceRegistry registry;
    private static Metadata metadata;
    private static SessionFactory factory;
    private static CountryDAOImpl dao;
    private static CountryServiceImpl countryMyService;
    private static CountryController controller;

    @BeforeClass
    public void setUp() throws Exception {
        if (isInitialized) return;
        registry = new StandardServiceRegistryBuilder().configure(HIBERNATE_CONFIG).build();
        metadata = new MetadataSources(registry).getMetadataBuilder().build();
        factory = metadata.getSessionFactoryBuilder().build();
        dao = new CountryDAOImpl(factory);
        countryMyService = new CountryServiceImpl(dao);
        controller = new CountryController(countryMyService);
        isInitialized = true;
    }

    @Test
    public void testGetCountryByNameInLowerCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountry("albania");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetCountryByNameInUpperCaseCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountry("ALBANIA");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetCountryByNameInRandomCaseCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountry("AlBanIa");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }


    @Test
    public void testGetCountryByNameHttpStatusNotFound() {
        ResponseEntity<Country> responseEntity = controller.getCountry("albani");

        assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    public void testGetCountryByDomainInLowerCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountries("a");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetCountryByDomainInUpperCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountries("A");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetCountryByDomainInRandomCaseHttpStatusOk() {
        ResponseEntity<Country> responseEntity = controller.getCountries("Az");

        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    public void testGetCountryByDomainHttpStatusNotFound() {
        ResponseEntity<Country> responseEntity = controller.getCountry("zzz");

        assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    public void testRefreshCountries() {
        controller.refreshCountries();
        ResponseEntity<Country> responseEntity = controller.getCountry("albania");

        assertEquals(responseEntity.getStatusCodeValue(), 200);

    }
}
