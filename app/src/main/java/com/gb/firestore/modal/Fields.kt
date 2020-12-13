package com.gb.firestore.modal

import com.google.gson.annotations.SerializedName

data class Fields(

    @SerializedName("app_version")
    var app_version: StringValue
)