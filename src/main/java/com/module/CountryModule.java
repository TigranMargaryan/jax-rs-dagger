package com.module;

import com.data.Country;
import dagger.Module;
import dagger.Provides;

@Module
public class CountryModule {

    @Provides
    Country providesCountry() {
        return new Country(888, "Tiko win");
    }
}
