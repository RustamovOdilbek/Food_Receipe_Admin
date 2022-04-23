package com.example.foodreceipe.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodreceipe.R
import com.example.foodreceipe.adapter.ProductAdapter
import com.example.foodreceipe.manager.DatabaseManager
import com.example.foodreceipe.manager.handler.DBPostsHandler
import com.example.foodreceipe.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : BaseActivty() {
    lateinit var recyclerView: RecyclerView
    lateinit var btn_add: ShapeableImageView
    var items = ArrayList<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        btn_add = findViewById(R.id.btn_add)
        btn_add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        loadUsers()
        refreshAdapter(items)
    }

    private fun loadUsers(): ArrayList<Post> {
        showLoading(this)
        DatabaseManager.loadUsers(object : DBPostsHandler {
            override fun onSuccess(users: ArrayList<Post>) {
                dismissLoading()
                items.clear()
                items.addAll(users)
                refreshAdapter(items)
            }

            override fun onError(e: Exception) {

            }

        })
        return items
    }

    private fun refreshAdapter(items: ArrayList<Post>) {
        val adapter = ProductAdapter(this, items)
        recyclerView.adapter = adapter
    }
}