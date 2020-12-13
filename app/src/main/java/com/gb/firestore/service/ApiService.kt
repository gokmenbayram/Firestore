package com.gb.firestore.service

import com.gb.firestore.modal.Version
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("(default)/documents/forceupdate")
    fun getVersion(): Call<Version>
}