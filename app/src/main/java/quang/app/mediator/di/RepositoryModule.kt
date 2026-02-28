package quang.app.mediator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.data.repository.AuthRepositoryImpl
import quang.app.mediator.domain.repository.AuthRepository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}
