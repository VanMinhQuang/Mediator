package quang.app.mediator.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quang.app.mediator.data.repository.AuthRepositoryImpl
import quang.app.mediator.data.repository.TopicRepositoryImpl
import quang.app.mediator.data.repository.UserRepositoryImpl
import quang.app.mediator.domain.repository.AuthRepository
import quang.app.mediator.domain.repository.TopicRepository
import quang.app.mediator.domain.repository.UserRepository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindTopicRepository(repositoryImpl: TopicRepositoryImpl): TopicRepository
}
