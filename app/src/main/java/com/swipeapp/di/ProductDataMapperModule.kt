package com.swipeapp.di


import com.swipeapp.data.mappers.ProductResponseMapper
import org.koin.dsl.module

val productDataMapperModule = module {
    single { ProductResponseMapper() }
}