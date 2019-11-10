package service;

import main.com.test.task.aleshin.dmitriy.countries.JsonLoader;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import java.util.List;

public class JsonLoaderTest extends TestCase{
    JsonLoader jsonLoader;
    
    @Before
    public void setUp() throws Exception {
        jsonLoader = new JsonLoader();
    }


    @Test
    public void testGetJsonObjectsNotNull() {
        List<JSONObject> jsonObjects = jsonLoader.getJsonObjects();

        assertNotNull(jsonObjects);
    }

    @Test
    public void testGetJsonObjectsCountriesExists() {
        List<JSONObject> jsonObjects = jsonLoader.getJsonObjects();

        assertTrue(jsonObjects.size() > 0);
    }
}
