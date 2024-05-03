package com.cricheroestest.data.network.response


import com.google.gson.annotations.SerializedName

data class PageX(
    @SerializedName("next")
    val next: String?
)