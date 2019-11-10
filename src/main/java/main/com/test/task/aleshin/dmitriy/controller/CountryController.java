package main.com.test.task.aleshin.dmitriy.controller;

import main.com.test.task.aleshin.dmitriy.countries.MyService;
import main.com.test.task.aleshin.dmitriy.countries.model.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    @Qualifier("myService")
    MyService service;

    public CountryController(MyService service) {
        this.service = service;
    }

    @GetMapping("/countries/name/{name}")
    public ResponseEntity getCountry(@PathVariable String name) {

        Optional<Country> country = service.getCountryByName(name);
        return country.map(c -> new ResponseEntity(country, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
        }

    @GetMapping("/countries/domain/{domain}")
    public ResponseEntity getCountries(@PathVariable String domain) {
        Optional<List<Country>> countries = service.getCountriesByDomain(domain);

        return countries.isPresent() ? countries.get().size() > 0
                ? new ResponseEntity(countries, HttpStatus.OK) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/countries/")
    public void refreshCountries() {
        service.refreshCountries();
    }
}
