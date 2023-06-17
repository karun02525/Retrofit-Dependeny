package com.swipeapp.di

import android.content.Context
import com.swipeapp.network.di.independentNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinLoader {

    fun load(application: Context) {
        val moduleList = productModulesAggregation + independentNetworkModule
        startKoin {
            androidContext(application)
            modules(moduleList)
        }
    }
}
