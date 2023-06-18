package com.swipeapp.network.entity.product
import com.google.gson.annotations.SerializedName


data class ApiAddProduct(
    @SerializedName("message")
    val message: String,
    @SerializedName("product_details")
    val productDetails: ProductDetails,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("success")
    val success: Boolean
)

data class ProductDetails(
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("tax")
    val tax: Double
)