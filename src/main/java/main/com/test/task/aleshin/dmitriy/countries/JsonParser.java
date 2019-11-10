package main.com.test.task.aleshin.dmitriy.countries;

import main.com.test.task.aleshin.dmitriy.countries.model.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    private List<Currency> currencies;
    private List<Language> languages;
    private List<RegionalBloc> regionalBlocs;

    public List<Country> getCountriesFromJson(List<JSONObject> jsonObjects) {
        return getCountries(jsonObjects);
    }

    private List<Country> getCountries(List<JSONObject> jsonObjects) {
        List<Country> countries = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjects) {

            JSONArray jsonArrayCurrencies = (JSONArray) jsonObject.get("currencies");
            parseElementsFromJsonArray(jsonArrayCurrencies, JsonElementName.CURRENCY);

            JSONArray jsonArrayLanguages = (JSONArray) jsonObject.get("languages");
            parseElementsFromJsonArray(jsonArrayLanguages, JsonElementName.LANGUAGE);

            JSONArray jsonArrayRegionalBlocs = (JSONArray) jsonObject.get("regionalBlocs");
            parseElementsFromJsonArray(jsonArrayRegionalBlocs, JsonElementName.REGIONAL_BLOC);

            Translation translation = getParsingTranslation((JSONObject) jsonObject.get("translations"));

            String name = (String) jsonObject.get("name");
            long population = jsonObject.get("population") != null ? (long) jsonObject.get("population") : 0;
            String flag = (String) jsonObject.get("flag");
            List<Domain> topLevelDomain = getParsingListDomains(jsonObject.get("topLevelDomain"));
            List<Latlng> latlng = getParsingListLatlngs(jsonObject.get("latlng"));

            Country country = new Country();

            country.setName(name);
            country.setTopLevelDomain(topLevelDomain);
            country.setPopulation(population);
            country.setLatlng(latlng);
            country.setFlag(flag);
            country.setCurrencies(currencies);
            country.setLanguages(languages);
            country.setTranslations(translation);
            country.setRegionalBlocs(regionalBlocs);

            countries.add(country);
        }
        return countries;
    }

    private void parseElementsFromJsonArray(JSONArray jsonArray, JsonElementName elementName) {

        switch (elementName) {
            case CURRENCY:
                currencies = new ArrayList<>(jsonArray.size());

                jsonArray.forEach(j -> {
                    Currency currency = getParsingCurrency((JSONObject) j);
                    currencies.add(currency);
                });
                return;
            case LANGUAGE:
                languages = new ArrayList<>(jsonArray.size());

                jsonArray.forEach(j -> {
                    Language language = getParsingLanguage((JSONObject) j);
                    languages.add(language);
                });
                return;
            case REGIONAL_BLOC:
                regionalBlocs = new ArrayList<>(jsonArray.size());

                jsonArray.forEach(j -> {
                    RegionalBloc regionalBloc = getParsingRegionalBloc((JSONObject) j);
                    regionalBlocs.add(regionalBloc);
                });
        }
    }

    private Currency getParsingCurrency(JSONObject jsonCurrencyElement) {
        Currency currency = new Currency();

        currency.setCode((String) jsonCurrencyElement.get("code"));
        currency.setName((String) jsonCurrencyElement.get("name"));
        currency.setSymbol((String) jsonCurrencyElement.get("symbol"));


        return currency;
    }

    private Language getParsingLanguage(JSONObject jsonLanguageElement) {
        Language language = new Language();

        language.setIso639_1((String) jsonLanguageElement.get("iso639_1"));
        language.setIso639_2((String) jsonLanguageElement.get("iso639_2"));
        language.setName((String) jsonLanguageElement.get("name"));
        language.setNativeName(((String) jsonLanguageElement.get("nativeName")));

        return language;
    }

    private RegionalBloc getParsingRegionalBloc(JSONObject jsonLanguageElement) {
        RegionalBloc regionalBlocs = new RegionalBloc();

        List<OtherAcronym> otherAcronyms = getParsingListOtherAcronyms((JSONArray) jsonLanguageElement.get("otherAcronyms"));
        List<OtherName> otherNames = getParsingListOtherNames((JSONArray) jsonLanguageElement.get("otherNames"));

        regionalBlocs.setAcronym((String) jsonLanguageElement.get("acronym"));
        regionalBlocs.setName((String) jsonLanguageElement.get("name"));
        regionalBlocs.setOtherAcronyms(otherAcronyms);
        regionalBlocs.setOtherNames(otherNames);

        return regionalBlocs;
    }

    private List<OtherAcronym> getParsingListOtherAcronyms(JSONArray jsonArrayOtherAcronyms) {
        List<OtherAcronym> otherAcronyms = new ArrayList<>(jsonArrayOtherAcronyms.size());

        for (Object jsonObjectOtherAcronym : jsonArrayOtherAcronyms.toArray()) {
            OtherAcronym otherAcronym = getParsingOtherAcronym(jsonObjectOtherAcronym);
            otherAcronyms.add(otherAcronym);
        }
        return otherAcronyms;
    }

    private OtherAcronym getParsingOtherAcronym(Object jsonObject) {
        OtherAcronym otherAcronym = new OtherAcronym();
        otherAcronym.setOtherAcronym((String) jsonObject);

        return otherAcronym;
    }

    private List<OtherName> getParsingListOtherNames(JSONArray jsonArrayOtherAcronyms) {
        List<OtherName> otherAcronyms = new ArrayList<>(jsonArrayOtherAcronyms.size());

        for (Object jsonObjectOtherAcronym : jsonArrayOtherAcronyms.toArray()) {
            OtherName otherName = getParsingOtherName(jsonObjectOtherAcronym);
            otherAcronyms.add(otherName);
        }
        return otherAcronyms;
    }

    private OtherName getParsingOtherName(Object jsonObject) {
        OtherName otherName = new OtherName();
        otherName.setOtherName((String) jsonObject);

        return otherName;
    }

    private List<Domain> getParsingListDomains(Object jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject;

        List<Domain> domains = new ArrayList<>(jsonArray.size());

        for (Object aJsonArray : jsonArray) {
            Domain domain = new Domain();
            domain.setDefinition((String) aJsonArray);
            domains.add(domain);
        }
        return domains;
    }

    private List<Latlng> getParsingListLatlngs(Object jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject;

        List<Latlng> latlngs = new ArrayList<>(jsonArray.size());

        for (Object aJsonArray : jsonArray) {
            Latlng latlng = new Latlng();
            latlng.setLatlng((double) aJsonArray);
            latlngs.add(latlng);
        }
        return latlngs;
    }

    private Translation getParsingTranslation(JSONObject jsonObject) {
        Translation translation = new Translation();

        translation.setPt((String) jsonObject.get("pt"));
        translation.setJa((String) jsonObject.get("ja"));
        translation.setIt((String) jsonObject.get("it"));
        translation.setFr((String) jsonObject.get("fr"));
        translation.setFa((String) jsonObject.get("fa"));
        translation.setEs((String) jsonObject.get("es"));
        translation.setDe((String) jsonObject.get("de"));
        translation.setBr((String) jsonObject.get("br"));

        return translation;
    }
}
