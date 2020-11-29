package com.module;

import com.manager.CountryService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class CountryManagerModule {

    @Provides
    CountryService provideServers(@Named("another") String server){
        return new CountryService(server);
    }

    @Provides
    @Named("another")
    String provideAnother() {
        return "http://www.google.com";
    }
}
