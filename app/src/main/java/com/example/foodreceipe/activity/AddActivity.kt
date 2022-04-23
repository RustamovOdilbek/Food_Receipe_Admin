package com.example.foodreceipe.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.foodreceipe.R
import com.example.foodreceipe.manager.DatabaseManager
import com.example.foodreceipe.manager.StorageManager
import com.example.foodreceipe.manager.handler.DBPostHandler
import com.example.foodreceipe.manager.handler.StorageHandler
import com.example.foodreceipe.model.Post
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter

class AddActivity : BaseActivty() {
    lateinit var et_name: TextView
    lateinit var et_prise: TextView
    lateinit var iv_picture: ImageView
    lateinit var btn_add: Button

    var pickedPhoto: Uri? = null
    var allPhotos = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        initViews()
    }

    private fun initViews() {
        et_name = findViewById(R.id.et_name)
        et_prise = findViewById(R.id.et_price)

        iv_picture  = findViewById(R.id.iv_picture)
        iv_picture.setOnClickListener {
            pickFishBunPhoto()
        }

        btn_add = findViewById(R.id.btn_add)
        btn_add.setOnClickListener {
            showLoading(this)
            StorageManager.uploadPostPhoto(pickedPhoto!!, object : StorageHandler {
                override fun onSuccess(userImg: String) {
                    val post = Post(et_name.text.toString(), et_prise.text.toString(), userImg)
                    uploadNewPost(post)
                }

                override fun onError(e: Exception) {

                }

            })
        }
    }

    private fun uploadNewPost(post: Post) {

        DatabaseManager.storePost(post, object : DBPostHandler {
            override fun onSuccess(post: Post?) {
                dismissLoading()
                finish()
            }

            override fun onError(e: Exception) {

            }

        })
    }


    private fun pickFishBunPhoto() {
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setMinCount(1)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }


    private val photoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK){
                allPhotos =
                    it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
                pickedPhoto = allPhotos.get(0)
                showPickedPhoto()
            }
        }

    private fun showPickedPhoto() {
        iv_picture.setImageURI(pickedPhoto)
    }
}