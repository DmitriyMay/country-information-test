package service;

import main.com.test.task.aleshin.dmitriy.countries.model.Country;
import main.com.test.task.aleshin.dmitriy.countries.JsonLoader;
import main.com.test.task.aleshin.dmitriy.countries.JsonParser;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class JsonParserTest extends TestCase {

    JsonLoader jsonLoader;
    JsonParser jsonParser;

    @Override
    public void setUp() throws Exception {
        jsonLoader = new JsonLoader();
        jsonParser = new JsonParser();

    }

    @Test
    public void testGetCountriesFromJsonNotNull() {
        List<Country> countries = jsonParser.getCountriesFromJson(jsonLoader.getJsonObjects());

        assertNotNull(countries);
    }

    @Test
    public void testGetCountriesFromJsonExists() {
        List<Country> countries = jsonParser.getCountriesFromJson(jsonLoader.getJsonObjects());

        assertTrue(countries.size() > 0);
    }
}
