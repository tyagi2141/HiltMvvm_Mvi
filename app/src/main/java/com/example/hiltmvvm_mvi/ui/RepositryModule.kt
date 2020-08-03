package com.example.hiltmvvm_mvi.ui

import android.content.Context
import com.example.hiltmvvm_mvi.mappers.NetwokMappers
import com.example.hiltmvvm_mvi.repositry.MainRepositry
import com.example.hiltmvvm_mvi.retrofit.BlogRetrofit
import com.example.hiltmvvm_mvi.room.database.BlogDao
import com.example.hiltmvvm_mvi.room.mappers.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by Rahul on 02/08/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositryModule {

    @Singleton
    @Provides
    fun providerRepositry(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        netwokMappers: NetwokMappers,
        context: Context
    ): MainRepositry {
        return MainRepositry(blogDao, blogRetrofit, cacheMapper, netwokMappers,context)

    }
}

