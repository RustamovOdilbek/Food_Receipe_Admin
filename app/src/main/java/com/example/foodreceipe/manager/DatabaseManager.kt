package com.example.foodreceipe.manager

import com.example.foodreceipe.manager.handler.DBPostHandler
import com.example.foodreceipe.manager.handler.DBPostsHandler
import com.example.foodreceipe.model.Post
import com.google.firebase.firestore.FirebaseFirestore

private var USER_PATH = "list"

object DatabaseManager {
    private var database = FirebaseFirestore.getInstance()

    fun storePost(post: Post, handler: DBPostHandler){
        database.collection(USER_PATH).document().set(post).addOnCompleteListener {
            handler.onSuccess()
        }.addOnFailureListener {
            handler.onError(it)
        }
    }

    fun loadUsers(handler: DBPostsHandler){
        database.collection(USER_PATH).get().addOnCompleteListener {
            val users = ArrayList<Post>()
            if (it.isSuccessful) {
                for (document in it.result) {
                    val name = document.getString("name")
                    val price = document.getString("price")
                    val postImg = document.getString("postImg")
                    val post = Post(name!!, price!!, postImg!!)
                    users.add(post)
                }
                handler.onSuccess(users)
            }else{
                handler.onError(it.exception!!)
            }
        }
    }
}