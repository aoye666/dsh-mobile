package com.deepseek.dshmobile.di

import android.content.Context
import com.deepseek.dshmobile.service.DshEngineManager
import com.deepseek.dshmobile.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideDshEngineManager(@ApplicationContext context: Context): DshEngineManager {
        return DshEngineManager(context)
    }
}
