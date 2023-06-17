package com.swipeapp.network.service

import com.swipeapp.network.HttpCode
import com.swipeapp.network.entity.product.ApiProductItem
import com.swipeapp.network.error.ApiErrorParser
import com.swipeapp.network.response.Either


class ProductNetworkService(
    private val productApiService: ProductApiService,
    private val apiErrorParser: ApiErrorParser
) {

    suspend fun getProducts1(): Either<List<ApiProductItem>> {
        val response = productApiService.getProducts1()
        val body = response.body()
        val results = body?.data

        if (!response.isSuccessful
            || body == null
            || body.code != HttpCode.SUCCESS
            || results == null
        ) {
            val error = apiErrorParser.extractError(response)
            return Either.failure(error)
        }
        return Either.success(results.apiProductItem)
    }

    suspend fun getProducts(): Either<List<ApiProductItem>> {
        val response = productApiService.getProducts()
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            val error = apiErrorParser.extractError(response)
            return Either.failure(error)
        }
        return Either.success(body)
    }
}
