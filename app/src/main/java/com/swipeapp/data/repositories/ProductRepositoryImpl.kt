package com.swipeapp.data.repositories

import com.swipeapp.data.mappers.ProductResponseMapper
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either
import com.swipeapp.network.response.map
import com.swipeapp.network.service.ProductNetworkService
import java.io.InputStream


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

    override suspend fun addProducts(
        product_name: String,
        product_type: String,
        product_price: String,
        product_tax: String,
        inputStream: InputStream?
    ): Either<Boolean> {
        return productNetworkService.addProducts(
            product_name,
            product_type,
            product_price,
            product_tax,
            inputStream
        )
    }
}