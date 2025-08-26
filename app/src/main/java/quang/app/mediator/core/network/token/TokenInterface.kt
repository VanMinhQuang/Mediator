package quang.app.mediator.core.network.token

interface TokenInterface {
    fun getToken(): String?
    fun saveToken(token: String)
    fun clearToken()
}