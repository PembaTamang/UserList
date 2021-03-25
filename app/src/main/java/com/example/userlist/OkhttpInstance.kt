package com.example.userlist

import okhttp3.OkHttpClient

class OkhttpInstance {
    companion object{
        fun getInstance(): OkHttpClient {
            return  OkHttpClient()
        }
        const val url = "https://gorest.co.in/public-api/users"
    }
}