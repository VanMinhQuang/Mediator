package quang.app.mediator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.core.network.NetworkService
import quang.app.mediator.core.supabase.SupabaseService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        // You can specify the timeout here explicitly
        return NetworkService(defaultTimeout = 10_000L)
    }


    @Provides
    @Singleton
    fun provideSupabaseService(): SupabaseService {
        return SupabaseService()
    }






}
