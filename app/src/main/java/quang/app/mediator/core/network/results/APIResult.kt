package quang.app.mediator.core.network.results

sealed class APIResult<out T>{
    data class Success<out T>(val data: T): APIResult<T>()
    data class Error(val error: NetworkError): APIResult<Nothing>()
}