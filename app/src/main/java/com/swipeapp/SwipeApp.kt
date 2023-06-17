package com.swipeapp

import android.app.Application
import com.swipeapp.di.KoinLoader

class SwipeApp : Application(){

    override fun onCreate() {
        super.onCreate()
        KoinLoader.load(this@SwipeApp )
    }

}