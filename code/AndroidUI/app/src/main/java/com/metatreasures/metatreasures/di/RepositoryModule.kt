package com.metatreasures.metatreasures.di

import com.metatreasures.metatreasures.data.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTokenRepository(client: OkHttpClient): TokenRepository {
        return TokenRepository(client)
    }
}