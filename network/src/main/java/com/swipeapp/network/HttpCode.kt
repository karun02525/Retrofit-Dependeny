package com.swipeapp.network

object HttpCode {

    const val SUCCESS = 200
    const val CREATED = 201
    const val NO_CONTENT = 204

    fun getSuccessResponseCodeList(): List<Int> = listOf(SUCCESS, CREATED, NO_CONTENT)
}