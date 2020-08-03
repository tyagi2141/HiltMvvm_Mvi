package com.example.hiltmvvm_mvi

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hiltmvvm_mvi.model.Blog
import com.example.hiltmvvm_mvi.ui.MainStateEvent
import com.example.hiltmvvm_mvi.ui.MainStateEvent.GetBlogEvents
import com.example.hiltmvvm_mvi.ui.MainViewModel
import com.example.hiltmvvm_mvi.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribe()
        viewModel.setStateEvent(GetBlogEvents())
    }

    fun subscribe() {

        viewModel.dataState.observe(this, Observer {

            when (it) {

                is DataState.success -> {
                    progressBar(false)
                    appendBlogTitle(it.data)
                }
                is DataState.error -> {
                    progressBar(false)
                    displayMessae(it.exception.message!!)
                }
                is DataState.Loading -> {
                    progressBar(true)
                }

            }
        })
    }

    private fun displayMessae(message: String) {
        if (message != null) {
            text.text = message
        }
    }

    private fun progressBar(isdisplay: Boolean) {
        progress_bar.visibility = if (isdisplay) View.INVISIBLE else View.INVISIBLE
    }

    private fun appendBlogTitle(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}