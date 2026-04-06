package quang.app.mediator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.data.remote.auth.AuthRemote
import quang.app.mediator.data.remote.auth.AuthRemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    abstract fun bindAuthRemote(
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthRemote
}
