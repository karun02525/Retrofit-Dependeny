package com.swipeapp.data.mappers

import com.swipeapp.domain.model.ProductModel
import com.swipeapp.network.entity.product.ApiProductItem


class ProductResponseMapper : Mapper<ApiProductItem, ProductModel> {

    override fun toDomain(from: ApiProductItem): ProductModel {
        return ProductModel(
            productName = from.productName,
            productType = from.productType,
            price = from.price,
            tax = from.tax,
            image = from.image
        )
    }
}
