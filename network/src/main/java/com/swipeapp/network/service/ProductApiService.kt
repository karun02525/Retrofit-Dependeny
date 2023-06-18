package com.swipeapp.network.service

import com.swipeapp.network.Api
import com.swipeapp.network.entity.product.ApiAddProduct
import com.swipeapp.network.entity.product.ApiProduct
import com.swipeapp.network.entity.product.ApiProductItem
import com.swipeapp.network.response.BaseResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProductApiService {

    @GET(Api.Url.PRODUCT_LIST)
    suspend fun getProducts1(): Response<BaseResponse<ApiProduct>>

    @GET(Api.Url.PRODUCT_LIST)
    suspend fun getProducts(): Response<List<ApiProductItem>>

    @Multipart
    @POST(Api.Url.PRODUCT_ADD)
    suspend fun addProducts(
        @Part picture: MultipartBody.Part?,
        @Part(Api.Param.PRODUCT_NAME) productName: RequestBody,
        @Part(Api.Param.PRODUCT_TYPE) productType: RequestBody,
        @Part(Api.Param.PRODUCT_PRICE) price: RequestBody,
        @Part(Api.Param.PRODUCT_TAX) tax: RequestBody,
    ): Response<ApiAddProduct>
}
