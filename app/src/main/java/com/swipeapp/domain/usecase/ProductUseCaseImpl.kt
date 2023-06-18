package com.swipeapp.domain.usecase

import android.content.Context
import android.net.Uri
import com.swipeapp.data.repositories.ProductRepository
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either

class ProductUseCaseImp(
    private val repository: ProductRepository,
    private val context: Context
) : ProductUseCase {
    override suspend fun getProduct(): Either<List<ProductModel>> {
        return repository.getProducts()
    }

    override suspend fun invoke(
        product_name: String,
        product_type: String,
        product_price: String,
        product_tax: String,
        uri: Uri?
    ): Either<Boolean> {
        return try {
            repository.addProducts(
                product_name,
                product_type,
                product_price,
                product_tax,
                inputStream = uri?.let { context.contentResolver.openInputStream(it) }
            )
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }
}