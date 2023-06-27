package com.example.fastfood.model.autoComplete


import com.google.gson.annotations.SerializedName

data class AutoCompleteResponseItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imageType")
    val imageType: String? = null,
    @SerializedName("title")
    val title: String? = null
)