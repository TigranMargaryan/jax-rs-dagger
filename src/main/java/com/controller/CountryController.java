package com.controller;

import com.Resource.CountryResource;
import com.data.Country;
import com.manager.CountryService;
import com.manager.IM.DaggerCountryComponent;
import com.module.CountryManagerModule;
import com.module.CountryModule;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/countries")
public class CountryController {

    @Inject
    CountryService countryService;

    public CountryController(){
        DaggerCountryComponent
                .builder()
                .countryManagerModule(new CountryManagerModule()).countryModule(new CountryModule())
                .build().inject(this);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> getCountries(){

        return countryService.getAllCountries();
    }

    @POST
    @Path("/post/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCountry(CountryResource countryResource){
        countryService.createCountry(countryResource);
        return Response.status(201).entity(countryResource).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryById(@PathParam("id") int id)
    {
        Country country = countryService.getCountryById(id);

            if(country != null){
                return Response.ok(country).build();
            }
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        }


    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCountry(@PathParam("id") int id, CountryResource countryResource)
    {
        countryService.updateCountry(countryResource, id);

                return Response.ok(countryResource).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCountry(@PathParam("id") int id){
        countryService.deleteCountry(id);
        return Response.status(Response.Status.OK).entity("ACCEPTED").build();
    }
}
