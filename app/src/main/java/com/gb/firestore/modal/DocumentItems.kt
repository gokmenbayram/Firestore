package com.gb.firestore.modal

import com.google.gson.annotations.SerializedName

data class DocumentItems(

    @SerializedName("name")
    var name: String,
    @SerializedName("fields")
    var fields: Fields,
    @SerializedName("createdTime")
    var createdTime: String,
    @SerializedName("updateTime")
    var updateTime: String,

)
