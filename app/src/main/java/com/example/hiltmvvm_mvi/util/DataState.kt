package com.example.hiltmvvm_mvi.util

import java.lang.Exception

/**
 * Created by Rahul on 02/08/20.
 */
sealed class DataState<out R> {
    data class success<out T>(val data:T):DataState<T>()
    data class error(val exception: Exception):DataState<Nothing>()
    object Loading:DataState<Nothing>()
}