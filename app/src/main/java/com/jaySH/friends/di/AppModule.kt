package com.jaySH.friends.di

import com.jaySH.friends.domain.user.InMemoryUserCatalog
import com.jaySH.friends.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesInMemoryUserCatalog(): InMemoryUserCatalog {
        return InMemoryUserCatalog(usersForPassword = mutableMapOf())
    }

    @Provides
    @Singleton
    fun providesUserRepository(userCatalog: InMemoryUserCatalog): UserRepository {
        return UserRepository(userCatalog = userCatalog)
    }
}