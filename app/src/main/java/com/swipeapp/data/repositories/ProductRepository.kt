package com.swipeapp.data.repositories

import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either
import java.io.InputStream


interface ProductRepository {

    suspend fun getProducts(): Either<List<ProductModel>>

    suspend fun addProducts(product_name: String,
                            product_type: String,
                            product_price: String,
                            product_tax: String,
                            inputStream: InputStream?): Either<Boolean>


}
