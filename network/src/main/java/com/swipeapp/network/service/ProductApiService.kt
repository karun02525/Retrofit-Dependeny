package com.swipeapp.network.service

import com.swipeapp.network.Api
import com.swipeapp.network.entity.product.ApiProduct
import com.swipeapp.network.entity.product.ApiProductItem
import com.swipeapp.network.response.BaseResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductApiService {

    @GET(Api.Url.PRODUCT_LIST)
    suspend fun getProducts1(): Response<BaseResponse<ApiProduct>>

    @GET(Api.Url.PRODUCT_LIST)
    suspend fun getProducts(): Response<List<ApiProductItem>>

}
