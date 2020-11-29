package com.test;

import com.data.Country;
import com.manager.CountryService;
import com.manager.IM.CountryComponent;
import com.manager.IM.DaggerCountryComponent;
import com.module.CountryManagerModule;
import com.module.CountryModule;
import com.module.MySqlModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class SimpleTest {

    @Inject
    Country country;

    @Inject
    CountryService countryService;

    CountryComponent countryComponent;

    @Before
    public void setUp(){
        countryComponent = DaggerCountryComponent
                .builder().countryModule(new CountryModule())
                .countryManagerModule(new CountryManagerModule())
                .mySqlModule(new MySqlModule()).build();


        DaggerCountryComponent.create().inject(this);
    }

    @Test
    public void testGetCountry(){
        Assert.assertNotNull(country.getId());
        Assert.assertNotNull(country.getCountryName());
    }

    @Test
    public void testSetCompany(){
        country.setId(8888);
        country.setCountryName("Tokyo");
        Assert.assertTrue( country.getId() == 8888);
        Assert.assertTrue( country.getCountryName().equals("Tokyo"));
        Assert.assertNotNull(country.toString());
    }
}
