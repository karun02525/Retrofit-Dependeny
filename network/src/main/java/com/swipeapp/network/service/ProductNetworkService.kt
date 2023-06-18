package com.swipeapp.network.service

import android.util.Log
import com.swipeapp.network.Api
import com.swipeapp.network.HttpCode
import com.swipeapp.network.entity.product.ApiProductItem
import com.swipeapp.network.error.ApiErrorParser
import com.swipeapp.network.response.Either
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream


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

    suspend fun addProducts(
        product_name: String,
        product_type: String,
        product_price: String,
        product_tax: String,
        inputStream: InputStream?
    ): Either<Boolean> {

        return try {
            val productName = product_name.toRequestBody(contentType = "text/plain".toMediaType())
            val productType = product_type.toRequestBody(contentType = "text/plain".toMediaType())
            val price = product_price.toRequestBody(contentType = "text/plain".toMediaType())
            val tax = product_tax.toRequestBody(contentType = "text/plain".toMediaType())

            val response = if (inputStream != null) {
                val fileByteArray = inputStream.readBytes()
                productApiService.addProducts(
                    picture = MultipartBody.Part.createFormData(
                        name = Api.Part.FILE,
                        filename = "${System.currentTimeMillis()}.png",
                        body = fileByteArray.toRequestBody(
                            contentType = "image/*".toMediaType()
                        )
                    ),
                    productName = productName,
                    productType = productType,
                    price = price,
                    tax = tax
                )
            } else {
                productApiService.addProducts(
                    picture = null, productName = productName,
                    productType = productType, price = price, tax = tax
                )
            }

            val body = response.body()
            if (!response.isSuccessful || body == null) {
                val error = apiErrorParser.extractError(response)
                return Either.failure(error)
            }
            return Either.success(body.success)
        } catch (error: Throwable) {
            Log.d("TAG", "addProducts: "+error.message)
            Either.failure(error)
        }
    }
}
