package com.cricheroestest.data.network.response


import com.google.gson.annotations.SerializedName

data class Reply(
    @SerializedName("data")
    val `data`: List<ReplyList>?,
    @SerializedName("page")
    val page: Page?
)