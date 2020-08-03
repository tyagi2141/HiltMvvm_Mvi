package com.example.hiltmvvm_mvi.repositry

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.hiltmvvm_mvi.mappers.NetwokMappers
import com.example.hiltmvvm_mvi.model.Blog
import com.example.hiltmvvm_mvi.retrofit.BlogRetrofit
import com.example.hiltmvvm_mvi.room.database.BlogDao
import com.example.hiltmvvm_mvi.room.mappers.CacheMapper
import com.example.hiltmvvm_mvi.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Rahul on 02/08/20.
 */
class MainRepositry(
    val blogDao: BlogDao,
    val blogRetrofit: BlogRetrofit,
    val cacheMapper: CacheMapper,
    val networkMappers: NetwokMappers,
val context: Context?
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        try {
            if (isNetworkAvailable(context)){
            val networkBlog = blogRetrofit.get()
            val blogs = networkMappers.mapFromEntityList(networkBlog)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }}
            val cacheBlod = blogDao.get()
            emit(DataState.success(cacheMapper.mapFromEntityList(cacheBlod)))
        } catch (e: Exception) {
            emit(DataState.error(e))
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            try {
                val activeNetworkInfo =
                    connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: java.lang.Exception) {
                return false
            }
        }
        return false
    }
}