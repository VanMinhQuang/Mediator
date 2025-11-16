package quang.app.mediator.data.model

data class MeditationModel(
    val id: String?,
    val title: String?,
    val subTitle: String?,
    val imageUrl: String?,
){
    companion object{
        fun fromJson(json: Map<String, Any>): MeditationModel {
            return MeditationModel(
                id = json["id"] as String?,
                title = json["title"] as String?,
                subTitle = json["subTitle"] as String?,
                imageUrl = json["imageUrl"] as String?,
            )
        }

        fun toJson(meditationModel: MeditationModel): Map<String, Any?> {
            return mapOf(
                "id" to meditationModel.id,
                "title" to meditationModel.title,
                "subTitle" to meditationModel.subTitle,
                "imageUrl" to meditationModel.imageUrl,
            )
        }

        fun dummyList(): List<MeditationModel> {
            return listOf(
                MeditationModel(
                    id = "1",
                    title = "Morning Meditation",
                    subTitle = "Start your day with calmness",
                    imageUrl = "https://picsum.photos/800"
                ),
                MeditationModel(
                    id = "2",
                    title = "Evening Relaxation",
                    subTitle = "Wind down and relax",
                    imageUrl = "https://picsum.photos/800"
                ),
                MeditationModel(
                    id = "3",
                    title = "Focus Booster",
                    subTitle = "Enhance your concentration",
                    imageUrl = "https://picsum.photos/800"
                ),
                MeditationModel(
                    id = "4",
                    title = "Stress Relief",
                    subTitle = "Let go of your worries",
                    imageUrl = "https://picsum.photos/800"
                ),
                MeditationModel(
                    id = "5",
                    title = "7 Days of Calm",
                    subTitle = "Enhance your concentration",
                    imageUrl = "https://picsum.photos/800"
                ),
                MeditationModel(
                    id = "6",
                    title = "Anxiet Release",
                    subTitle = "Let go of your worries",
                    imageUrl = "https://picsum.photos/800"
                ),

            )
        }
    }
}
