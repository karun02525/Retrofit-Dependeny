package com.swipeapp.domain.usecase

import com.swipeapp.data.repositories.ProductRepository
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.response.Either

class ProductUseCaseImp(private val repository: ProductRepository) : ProductUseCase {
    override suspend fun getProduct(): Either<List<ProductModel>> {
      return repository.getProducts()
    }
}