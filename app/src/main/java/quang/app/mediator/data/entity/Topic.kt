package quang.app.mediator.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Topic(

    @SerialName("id")
    val id: Int,
    @SerialName("topic_title")
    val topicTitle: String?,
    @SerialName("topic_type")
    val topicType: String?,
    @SerialName("topic_image")
    val topicImage: String?,
)
