package quang.app.mediator.core.network.results

sealed class NetworkError(message: String) : Throwable(message) {
    object Unauthorized : NetworkError("Unauthorized") {
        private fun readResolve(): Any = Unauthorized
    }

    object NotFound : NetworkError("Not Found") {
        private fun readResolve(): Any = NotFound
    }

    object Timeout : NetworkError("Timeout") {
        private fun readResolve(): Any = Timeout
    }

    data class Unknown(val errorMessage: String) : NetworkError(errorMessage)

}