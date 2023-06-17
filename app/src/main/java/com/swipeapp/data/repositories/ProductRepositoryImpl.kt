package com.swipeapp.data.repositories

import com.swipeapp.data.mappers.ProductResponseMapper
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either
import com.swipeapp.network.response.map
import com.swipeapp.network.service.ProductNetworkService


class ProductRepositoryImpl(
    private val productNetworkService: ProductNetworkService,
    private val productResponseMapper: ProductResponseMapper,
) : ProductRepository {
    override suspend fun getProducts(): Either<List<ProductModel>> {
        return try {
            productNetworkService.getProducts().map(productResponseMapper::toDomain)
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }
}