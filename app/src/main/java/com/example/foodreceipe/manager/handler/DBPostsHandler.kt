package com.example.foodreceipe.manager.handler

import com.example.foodreceipe.model.Post

interface DBPostsHandler {
    fun onSuccess(users: ArrayList<Post>)
    fun onError(e: Exception)
}