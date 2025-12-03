package com.metatreasures.metatreasures.di

import android.content.Context
import com.metatreasures.metatreasures.datastore.ThemeSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {
    @Provides
    @Singleton
    fun provideThemeSettings(@ApplicationContext context: Context): ThemeSettings {
        return ThemeSettings(context)
    }
}