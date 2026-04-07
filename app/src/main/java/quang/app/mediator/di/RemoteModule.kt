package quang.app.mediator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.data.remote.auth.AuthRemote
import quang.app.mediator.data.remote.auth.AuthRemoteDataSource
import quang.app.mediator.data.remote.query.user.UserConfigurationRemote
import quang.app.mediator.data.remote.query.user.UserConfigurationRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun bindAuthRemote(
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthRemote

    @Binds
    @Singleton
    abstract fun bindUserConfigurationRemote(
      user: UserConfigurationRemoteDataSource
    ): UserConfigurationRemote
}
