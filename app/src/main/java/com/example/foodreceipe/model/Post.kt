package com.example.foodreceipe.model

class Post {
    var name: String = ""
    var price: String = ""
    var postImg: String = ""

    constructor(name: String, price: String, postImg: String){
        this.name = name
        this.price = price
        this.postImg = postImg
    }
}