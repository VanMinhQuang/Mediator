package quang.app.mediator.domain.model

import quang.app.mediator.data.entity.Topic

data class MediationTopic (
    val id: Int,
    val title: String,
    val type: String,
    val imageUrl: String,
    val isPicked: Boolean = false
){
    companion object {
        /**
         * Creates a domain User from Supabase Auth User metadata.
         * Note: Most extra fields (firstName, lastName, etc.) are stored in the 'user_metadata' map
         */
        fun fromEntity(
            topic: Topic
        ): MediationTopic {
            return MediationTopic(
                id = topic.id,
                title = topic.topicTitle ?: "",
                type = topic.topicType ?: "",
                imageUrl = topic.topicImage ?: "",

            )
        }
    }
}
