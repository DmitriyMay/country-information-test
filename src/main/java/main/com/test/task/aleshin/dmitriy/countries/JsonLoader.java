package main.com.test.task.aleshin.dmitriy.countries;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonLoader {

    private static final String URL_IN = "https://restcountries.eu/rest/v2/all";
    private HttpURLConnection request;

    public JsonLoader() {
        init();
    }

    private void init() {
        request = getHttpConnection();
        connect(request);
    }

    private HttpURLConnection getHttpConnection() {
        return openConnection(getUrl());
    }

    private URL getUrl() {
        URL url = null;
        try {
            url = new URL(URL_IN);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid formed URL", e);
        }
        return url;
    }

    private HttpURLConnection openConnection(URL url) {
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return httpURLConnection;
    }

    private void connect(HttpURLConnection request) {
        try {
            request.connect();
        } catch (IOException e) {
            throw new RuntimeException("The requested resource is unavailable", e);
        }
    }

    public List<JSONObject> getJsonObjects() {
        JSONArray jsonArray = getFullJsonArray();

        List<JSONObject> jsonObjects = new ArrayList<>();

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            jsonObjects.add(jsonObject);
        }

        return jsonObjects;
    }

    private JSONArray getFullJsonArray() {
        return getParsingContent(new JSONParser(), getContent());
    }

    private Object getContent() {
        Object content = null;

        try {
            content = request.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return content;
    }

    private JSONArray getParsingContent(JSONParser jp, Object content) {
        JSONArray jsonArray = null;

        try {
            jsonArray = (JSONArray) jp.parse(new InputStreamReader((InputStream) content));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return jsonArray;
    }
}
