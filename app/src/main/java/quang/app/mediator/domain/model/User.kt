package quang.app.mediator.domain.model

import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val username: String?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val image: String?,

    ) {
    companion object {
        /**
         * Creates a domain User from Supabase Auth User metadata.
         * Note: Most extra fields (firstName, lastName, etc.) are stored in the 'user_metadata' map
         */
        fun fromUserInfo(
            supabaseUser: UserInfo?
        ): User {
            val metadata = supabaseUser?.userMetadata

            return User(
                id = supabaseUser?.id,
                email = supabaseUser?.email,
                username = metadata?.get("username")?.toString(),
                firstName = metadata?.get("first_name")?.toString(),
                lastName = metadata?.get("last_name")?.toString(),
                gender = metadata?.get("gender")?.toString(),
                image = metadata?.get("avatar_url")?.toString(),

            )
        }
    }
}
