package com.swipeapp.network.utils

import com.swipeapp.network.error.ApiErrorParser
import com.swipeapp.network.response.BaseResponse
import com.swipeapp.network.response.Either
import retrofit2.Response

suspend inline fun <T : Any> call(
    errorParser: ApiErrorParser,
    crossinline call: suspend () -> Response<BaseResponse<T>>
): Either<T> {
    return try {
        val response = call.invoke()
        val body = response.body()
        val result = body?.data

        if (!response.isSuccessful || body == null || result == null) {
            val error = errorParser.extractError(response)
            Either.failure(error)
        } else {
            Either.success(result)
        }
    } catch (error: Throwable) {
        Either.failure(error)
    }
}