package com.example.hiltmvvm_mvi.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hiltmvvm_mvi.room.model.BlogCacheEntity

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(blogEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun get(): List<BlogCacheEntity>
}