package com.swipeapp.di

import com.swipeapp.data.repositories.ProductRepository
import com.swipeapp.data.repositories.ProductRepositoryImpl
import com.swipeapp.domain.usecase.ProductUseCase
import com.swipeapp.domain.usecase.ProductUseCaseImp
import com.swipeapp.ui.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productDomainModule = module {

    single<ProductUseCase> { ProductUseCaseImp(get(),get()) }
    single<ProductRepository> { ProductRepositoryImpl(get(),get()) }
}