package com.example.hiltmvvm_mvi.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hiltmvvm_mvi.room.database.BlogDao
import com.example.hiltmvvm_mvi.room.database.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Rahul on 02/08/20.
 */

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideLocalDB(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(context, BlogDatabase::class.java, BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDAO(blogDatabase: BlogDatabase):BlogDao{
        return blogDatabase.blogDao()
    }

    @Singleton
    @Provides
    fun provideAppInctance(context: Application): Context {
        return context
    }
}