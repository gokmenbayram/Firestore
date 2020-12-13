package com.gb.firestore.modal

import com.google.gson.annotations.SerializedName

data class Version(

    @SerializedName("documents")
    var documents: List<DocumentItems>
)
