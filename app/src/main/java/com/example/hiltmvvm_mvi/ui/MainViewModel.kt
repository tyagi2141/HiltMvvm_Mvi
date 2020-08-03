package com.example.hiltmvvm_mvi.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmvvm_mvi.model.Blog
import com.example.hiltmvvm_mvi.repositry.MainRepositry
import com.example.hiltmvvm_mvi.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Rahul on 02/08/20.
 */
@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
  private val mainRepositry: MainRepositry,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: MutableLiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetBlogEvents -> {
                    mainRepositry.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}
sealed class MainStateEvent() {
    class GetBlogEvents() : MainStateEvent()
    class none() : MainStateEvent()
}