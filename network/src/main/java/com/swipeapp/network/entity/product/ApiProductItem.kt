package com.swipeapp.network.entity.product


import com.google.gson.annotations.SerializedName

data class ApiProductItem(
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("tax")
    val tax: String
)