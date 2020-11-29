package com.manager;

import com.Resource.CountryResource;
import com.data.Country;
import com.database.MySqlDb;
import com.manager.IM.DaggerCountryComponent;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class CountryService {

    private String server;

    @Inject
    public MySqlDb mySqlDb;

    @Inject
    public Country country;

    @Inject
    public CountryService(@Named("server") String server){
        DaggerCountryComponent.create().inject(this);
        this.server = server;
    }

    public List<Country> getAllCountries() {
        return mySqlDb.getAllCountries();
    }

    public Country getCountryById(Integer id) {
        return mySqlDb.getData(id);
    }

    public void createCountry(CountryResource country) {
        mySqlDb.addData(country);
    }

    public void updateCountry(CountryResource country, Integer id) {
        mySqlDb.updateData(country, id);
    }

    public void deleteCountry(int id){
        mySqlDb.deleteData(id);
    }
}
