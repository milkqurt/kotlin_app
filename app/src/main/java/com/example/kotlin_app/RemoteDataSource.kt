package com.example.kotlin_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesServiceApi {
    @GET("/name/{name}")
    //Gets the name of the country and passes it along Path to the request {}
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}

//collects the base url and adds the part getCountryByName
var retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v3.1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//implements the RestCountriesServiceApi interface
//http://restcountries.com/v3.1/name/russia
var restCountriesApi = retrofit.create(RestCountriesServiceApi::class.java)
