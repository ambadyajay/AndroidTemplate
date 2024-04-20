package com.example.androidprojecttemplate.core.networking.exceptions

sealed class NetworkExceptions(val exceptionMessage: String) : Exception() {
    // client errors
    object NoInternet :
        NetworkExceptions("Unable to connect. Please check your internet connection")

    object UnknownError : NetworkExceptions("Something went wrong. Please try again later")
    object BadRequest : NetworkExceptions("Bad Request")
    object Unauthorized : NetworkExceptions("Unauthorized")
    object PaymentRequired : NetworkExceptions("Payment Required")
    object Forbidden : NetworkExceptions("Forbidden")
    object NotFound : NetworkExceptions("The item you are looking for not found")
    object MethodNotAllowed : NetworkExceptions("Method Not Allowed")
    object NotAcceptable : NetworkExceptions("Not Acceptable")
    object ProxyAuthenticationRequired : NetworkExceptions("Proxy Authentication Required")
    object RequestTimeout : NetworkExceptions("Request Timeout")
    object Conflict : NetworkExceptions("Conflict")
    object Gone : NetworkExceptions("Gone")

    // server errors
    object InternalServerError : NetworkExceptions("Internal Server Error")
    object NotImplemented : NetworkExceptions("Not Implemented")
    object BadGateway : NetworkExceptions("Bad Gateway")
    object ServiceUnavailable : NetworkExceptions("Service Unavailable")
    object GatewayTimeout : NetworkExceptions("Gateway Timeout")
    object HttpVersionNotSupported : NetworkExceptions("HTTP Version Not Supported")
    object VariantAlsoNegotiates : NetworkExceptions("Variant Also Negotiates")
    object InSufficientStorage : NetworkExceptions("Insufficient Storage")
    object LoopDetected : NetworkExceptions("Loop Detected")
    object NotExtended : NetworkExceptions("Not Extended")
    object NetworkAuthenticationRequired : NetworkExceptions("Network Authentication Required")

    companion object {
        private val httpStatusCodes = mapOf(
            400 to BadRequest,
            401 to Unauthorized,
            402 to PaymentRequired,
            403 to Forbidden,
            404 to NotFound,
            405 to MethodNotAllowed,
            406 to NotAcceptable,
            407 to ProxyAuthenticationRequired,
            408 to RequestTimeout,
            409 to Conflict,
            410 to Gone,
            500 to InternalServerError,
            501 to NotImplemented,
            502 to BadGateway,
            503 to ServiceUnavailable,
            504 to GatewayTimeout,
            505 to HttpVersionNotSupported,
            506 to VariantAlsoNegotiates,
            507 to InSufficientStorage,
            508 to LoopDetected,
            510 to NotExtended,
            511 to NetworkAuthenticationRequired,
        )

        fun contains(statusCode: Int): Boolean {
            return httpStatusCodes.contains(statusCode)
        }

        fun get(statusCode: Int): NetworkExceptions {
            assert(httpStatusCodes.contains(statusCode)) {
                "Please check status code contains by contains() method"
            }
            return httpStatusCodes[statusCode]!!
        }
    }
}