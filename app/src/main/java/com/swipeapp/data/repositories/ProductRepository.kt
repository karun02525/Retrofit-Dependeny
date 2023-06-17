package com.swipeapp.data.repositories

import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either


interface ProductRepository {

    suspend fun getProducts(): Either<List<ProductModel>>
}
