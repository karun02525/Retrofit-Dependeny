package com.swipeapp.domain.usecase

import android.net.Uri
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either
import java.io.InputStream

interface ProductUseCase {
    suspend fun getProduct(): Either<List<ProductModel>>

    suspend fun invoke(product_name: String,
                            product_type: String,
                            product_price: String,
                            product_tax: String,
                            uri: Uri?): Either<Boolean>
}