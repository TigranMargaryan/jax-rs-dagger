package com.test;

import com.data.Country;
import com.manager.CountryService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SimpleTest_MembersInjector implements MembersInjector<SimpleTest> {
  private final Provider<Country> countryProvider;

  private final Provider<CountryService> countryServiceProvider;

  public SimpleTest_MembersInjector(
      Provider<Country> countryProvider, Provider<CountryService> countryServiceProvider) {
    assert countryProvider != null;
    this.countryProvider = countryProvider;
    assert countryServiceProvider != null;
    this.countryServiceProvider = countryServiceProvider;
  }

  public static MembersInjector<SimpleTest> create(
      Provider<Country> countryProvider, Provider<CountryService> countryServiceProvider) {
    return new SimpleTest_MembersInjector(countryProvider, countryServiceProvider);
  }

  @Override
  public void injectMembers(SimpleTest instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.country = countryProvider.get();
    instance.countryService = countryServiceProvider.get();
  }

  public static void injectCountry(SimpleTest instance, Provider<Country> countryProvider) {
    instance.country = countryProvider.get();
  }

  public static void injectCountryService(
      SimpleTest instance, Provider<CountryService> countryServiceProvider) {
    instance.countryService = countryServiceProvider.get();
  }
}
