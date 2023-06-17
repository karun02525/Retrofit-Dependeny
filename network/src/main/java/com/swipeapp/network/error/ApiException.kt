package com.swipeapp.network.error

class ApiException : Exception {
    var apiErrorMessage: String? = null
        private set
    var code = 0

    constructor() : super() {}
    constructor(message: String?) : super(message) {
        apiErrorMessage = message
    }

    constructor(message: String?, cause: Throwable?) : super(message, cause) {
        apiErrorMessage = message
    }

    constructor(cause: Throwable?) : super(cause) {}
}