package quang.app.mediator.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserConfiguration(
    @SerialName("user_id")
    val userId: String? = null,
    @SerialName("theme")
    val themeMode: String? = "light", // "light", "dark", or "system"
    @SerialName("notifications_enabled")
    val notificationsEnabled: Boolean = true,
    @SerialName("language")
    val language: String = "en",
    @SerialName("meditation_time")
    val meditateTime: String? = null,
    @SerialName("meditation_days")
    val meditateDays: List<String>? = null,
    @SerialName("user_topics")
    val userTopics: List<Int>? = null,
    @SerialName("finished_welcome")
    val finishWelcome: Boolean = false
)
