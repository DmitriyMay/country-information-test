package main.com.test.task.aleshin.dmitriy.countries.dao;

import main.com.test.task.aleshin.dmitriy.countries.model.*;
import main.com.test.task.aleshin.dmitriy.countries.model.Currency;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.persistence.NoResultException;

import java.util.*;

@Component("implCountryDAO")
public class CountryDAOImpl implements DAO<Country> {

    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    private Session session;

    public CountryDAOImpl(SessionFactory factory) {
        this.factory = factory;
        session = factory.openSession();
    }

    @PreDestroy
    private void closeSession() {
        session.close();
        factory.close();
    }

    @Override
    public void createCountry(Country country) {
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(country);
        save(country.getTopLevelDomain());
        save(country.getCurrencies());
        save(country.getLanguages());
        save(country.getRegionalBlocs());
        country.getRegionalBlocs()
                .forEach(r -> {
                    session.save(r);
                    save(r.getOtherAcronyms());
                    save(r.getOtherNames());
                });

        save(country.getLatlng());

        session.flush();
        transaction.commit();
    }

    private void save(List<?> list) {
        list.forEach(session::saveOrUpdate);
    }

    @Override
    public Optional<Country> getCountryByName(String searchName) {
        String hql = "from " + Country.class.getSimpleName() +
                " where lower(name) = '" + searchName.toLowerCase() + "'";
        Country country = getRequestSingleResult(hql);

        return Optional.ofNullable(country);
    }

    private Country getRequestSingleResult(String hql) {
        Country country = null;
        try {
            country = (Country) session.createQuery(hql).getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.toString());
        }
        return country;
    }

    @Override
    public Optional<List<Country>> getCountriesByDomain(String searchDomain) {
        String hql = "from " + Country.class.getSimpleName() +
                " where id in (select cou_id from " + Domain.class.getSimpleName() +
                " where lower(def) like '%" + searchDomain.toLowerCase() + "%')";

        List<Country> countries = getRequestResultList(hql);

        return Optional.ofNullable(countries);
    }

    private List<Country> getRequestResultList(String hql) {
        List<Country> countries = null;
        try {
            countries = session.createQuery(hql).getResultList();
        } catch (NoResultException e) {
            System.out.println(e.toString());
        }
        return countries;
    }

    public void deleteCountries() {
        String hqlDeleteDomain = "delete from " + Domain.class.getSimpleName();
        String hqlDeleteCurrency = "delete from " + Currency.class.getSimpleName();
        String hqlDeleteLanguage = "delete from " + Language.class.getSimpleName();
        String hqlDeleteOtherAcronym = "delete from " + OtherAcronym.class.getSimpleName();
        String hqlDeleteOtherName= "delete from " + OtherName.class.getSimpleName();
        String hqlDeleteRegionalBloc = "delete from " + RegionalBloc.class.getSimpleName();
        String hqlDeleteLatlng = "delete from " + Latlng.class.getSimpleName();
        String hqlDeleteCountry = "delete from " + Country.class.getSimpleName();
        String hqlDeleteTranslation = "delete from " + Translation.class.getSimpleName();


        Transaction transaction = session.beginTransaction();
        session.createQuery(hqlDeleteDomain).executeUpdate();
        session.createQuery(hqlDeleteCurrency).executeUpdate();
        session.createQuery(hqlDeleteLanguage).executeUpdate();
        session.createQuery(hqlDeleteOtherAcronym).executeUpdate();
        session.createQuery(hqlDeleteOtherName).executeUpdate();
        session.createQuery(hqlDeleteRegionalBloc).executeUpdate();
        session.createQuery(hqlDeleteLatlng).executeUpdate();
        session.createQuery(hqlDeleteCountry).executeUpdate();
        session.createQuery(hqlDeleteTranslation).executeUpdate();
        transaction.commit();
    }
}
