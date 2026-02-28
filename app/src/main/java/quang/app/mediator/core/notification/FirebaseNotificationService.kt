package quang.app.mediator.core.notification

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FirebaseNotificationService: FirebaseMessagingService() {
    @Inject
    lateinit var notificationHandler: NotificationHandler

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        notificationHandler.showNotification(
            title = title ?: "Default Title",
            body = body ?: "Default Body"
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // send token to backend via repository
    }
}
