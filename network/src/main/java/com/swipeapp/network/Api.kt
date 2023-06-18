package com.swipeapp.network

internal object Api {

    internal object Url {
        const val PRODUCT_LIST = "api/public/get"
        const val PRODUCT_ADD = "api/public/add"
    }

    internal object Param {
        const val PRODUCT_NAME = "product_name"
        const val PRODUCT_TYPE = "product_type"
        const val PRODUCT_PRICE = "price"
        const val PRODUCT_TAX = "tax"
    }
    internal object Part {
        const val FILE = "files"
        const val USER_MEDIA = "userMedia"
    }
}
