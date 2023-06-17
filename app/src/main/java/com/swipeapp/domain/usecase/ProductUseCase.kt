package com.swipeapp.domain.usecase

import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either

interface ProductUseCase {
    suspend fun getProduct(): Either<List<ProductModel>>
}