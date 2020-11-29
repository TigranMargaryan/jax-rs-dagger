package com.manager.IM;

import com.controller.CountryController;
import com.data.Country;
import com.manager.CountryService;
import com.module.CountryManagerModule;
import com.module.CountryModule;
import com.module.MySqlModule;
import com.test.SimpleTest;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CountryModule.class, CountryManagerModule.class, MySqlModule.class})
public interface CountryComponent {

    CountryService provideServers();
    Country providesCountry();

    void inject(CountryController countryController);
    void inject(CountryService countryService);

    void inject(SimpleTest simpleTest);
}
