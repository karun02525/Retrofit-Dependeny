package com.swipeapp.network.response

import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("message")
    val errorMessage: String? = null
}