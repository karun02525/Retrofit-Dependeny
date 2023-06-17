package com.swipeapp.network.response

import com.google.gson.annotations.SerializedName

class BaseResponse<Data> {
    @SerializedName("code")
    val code = 0

    @SerializedName(value = "msg", alternate = ["message"])
    val message: String? = null
    @SerializedName(value = "data")
    val data: Data? = null
}