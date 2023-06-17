package com.swipeapp.network.di

import com.google.gson.Gson
import com.swipeapp.network.HttpClientBuilder
import com.swipeapp.network.NetworkProvider
import com.swipeapp.network.error.ApiErrorParser
import com.swipeapp.network.service.ProductNetworkService
import org.koin.dsl.module

val independentNetworkModule = module {




    single { get<NetworkProvider>().provideProductApiService() }
    single { HttpClientBuilder() }
    single { NetworkProvider(get(),get()) }
    single {
        ProductNetworkService(
            get(),
            get()
        )
    }
    single { Gson() }
    single { ApiErrorParser(get()) }

}