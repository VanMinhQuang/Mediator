package quang.app.mediator.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Music(
    val id: Int,
    val title: String,
    val subTitle: String,
    val length: Int, // length in seconds
    val imageUrl: String
) : Parcelable {
    companion object {
        fun dummyList(): List<Music> {
            return listOf(
                Music(1, "Calm Waves", "Relaxing ocean sounds", 300, "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"),
                Music(2, "Forest Ambience", "Soothing forest sounds", 420, "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/396e9/MainBefore.jpg"),
                Music(3, "Rainfall", "Gentle rain sounds", 360, "https://www.shutterstock.com/image-photo/traveler-woman-arms-raised-triumph-260nw-2457990309.jpg"),
                Music(4, "Night Sky", "Peaceful night sounds", 480, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFYqoKTu_o3Zns2yExbst2Co84Gpc2Q1RJbA&s"),
                Music(5, "Mountain Breeze", "Refreshing mountain air sounds", 540, "https://images.ctfassets.net/hrltx12pl8hq/28ECAQiPJZ78hxatLTa7Ts/2f695d869736ae3b0de3e56ceaca3958/free-nature-images.jpg?fit=fill&w=1200&h=630")
            )
        }
    }
}
