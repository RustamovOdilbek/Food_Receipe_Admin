package com.example.foodreceipe.manager.handler

import com.example.foodreceipe.model.Post

interface DBPostHandler {
    fun onSuccess(post: Post? = null)
    fun onError(e: Exception)
}