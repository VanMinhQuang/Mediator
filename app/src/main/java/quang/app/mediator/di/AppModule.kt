package quang.app.mediator.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.core.notification.NotificationHandler
import quang.app.mediator.core.notification.NotificationHandlerImpl
import quang.app.mediator.data.remote.AuthApi
import quang.app.mediator.di.Constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNotificationHandler(@ApplicationContext context: Context): NotificationHandler {
        return NotificationHandlerImpl(context)
    }


}
