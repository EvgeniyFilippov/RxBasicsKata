package com.example.myapplication53

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit

internal class CountriesServiceSolved : CountriesService {
    override fun countryNameInCapitals(country: Country): Single<String> {
        return Single.just(country).map { countryItem -> countryItem.name.uppercase() }
    }

    override fun countCountries(countries: List<Country>): Single<Int> {
        return Single.just(countries).map { list -> list.size }
    }

    override fun listPopulationOfEachCountry(countries: List<Country>): Observable<Long> {
        return Observable.just(countries)
            .flatMap { list -> Observable.fromIterable(list).map { country -> country.population } }
    }

    override fun listNameOfEachCountry(countries: List<Country>): Observable<String> {
        return Observable.just(countries)
            .flatMap { e -> Observable.fromIterable(e) }
            .map { c -> c.name }
    }

    override fun listOnly3rdAnd4thCountry(countries: List<Country>): Observable<Country> {
        return Observable.fromIterable(countries)
//            .map { countriesList ->
//                val list = mutableListOf<Country>()
//                countriesList.forEachIndexed { index, country ->
//                    if (index % 2 == 0 || index % 3 == 0) {
//                        list.add(country)
//                    }
//                }
//                list
//            }
//            .flatMap { list -> Observable.fromIterable(list) }
            .skip(2)
            .take(2)
        //.map { e -> e.subList(2, 4) }
        //.flatMap { c -> Observable.fromIterable(c) }
    }

    override fun isAllCountriesPopulationMoreThanOneMillion(countries: List<Country>): Single<Boolean> {
        return Observable.fromIterable(countries)
            .all { country -> country.population > 1000000 }
//            .filter { t -> t.population > 1000000 }
//            .collectInto(mutableListOf<Country>(), { list, newCountry ->
//                list.add(newCountry)
//            })
//            .map { countryList -> countryList.size > 3 }
//        Observable.fromIterable(countries)
//        .filter { t -> t.population > 1000000 }
//        .count().map { count -> count > 3 }
    }

    override fun listPopulationMoreThanOneMillion(countries: List<Country>): Observable<Country> {
        return Observable.fromIterable(countries)
            .filter { c -> c.population > 1000000 }
    }

    override fun listPopulationMoreThanOneMillionWithTimeoutFallbackToEmpty(countriesFromNetwork: FutureTask<List<Country>>): Observable<Country>? {
        return Observable.fromFuture(countriesFromNetwork)
            .flatMap { list -> Observable.fromIterable(list) }
            .filter { country -> country.population > 1000000 }
            .timeout(2, TimeUnit.SECONDS, Observable.empty())
    }

    override fun getCurrencyUsdIfNotFound(
        countryName: String,
        countries: List<Country>
    ): Observable<String>? {
        return null // put your solution here
    }

    override fun sumPopulationOfCountries(countries: List<Country>): Observable<Long>? {
        return null
    }

    override fun mapCountriesToNamePopulation(countries: List<Country>): Single<Map<String, Long>>? {
        return null

    }

    override fun sumPopulationOfCountries(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Observable<Long>? {
        return null // put your solution here
    }

    override fun areEmittingSameSequences(
        countryObservable1: Observable<Country>,
        countryObservable2: Observable<Country>
    ): Single<Boolean>? {
        return null // put your solution here
    }
}
